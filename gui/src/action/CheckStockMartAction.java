package action;

import gui.MainPage;
import player.Player;
import stock.Stock;
import player.PlayerSystem;
import stock.StockSystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CheckStockMartAction implements IAction {
    @Override
    public void trigger() {
        Player player = PlayerSystem.getInstance().getCurrentPlayer();
        JFrame jFrame = new JFrame();
        JTable jTable;
        String[] columnHeader = {"股票名", "成交价", "涨跌幅", "持股"};
        String[][] rowData = new String[10][4];
        for (int i = 0; i < 10; i++) {
            Stock stock = StockSystem.getInstance().get(i);
            rowData[i][0] = stock.getName();
            rowData[i][1] = String.format("%.2f", stock.getPrice());
            rowData[i][2] = String.format("%.2f", stock.getRate()*100 - 100) + "%";
            rowData[i][3] = String.format("%d", player.getStockAccount().getStockNum()[i]);
        }

        jTable = new JTable(new DefaultTableModel(rowData, columnHeader) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        jTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int id = jTable.getSelectedRow();
                    Stock stock = StockSystem.getInstance().get(id);
                    int stockOperationIndex = JOptionPane.showOptionDialog(null,
                            "请选择你要进行的操作", stock.getName(),
                            JOptionPane.YES_NO_CANCEL_OPTION,
                            JOptionPane.QUESTION_MESSAGE, null, new Object[] {
                                    "购入", "卖出", "取消" }, null);
                    // 判断要进行什么业务
                    if (stockOperationIndex == 0) {
                        // 玩家选择购入
                        int num = Integer.parseInt(JOptionPane.showInputDialog("你想购买几股？"));
                        if (PlayerSystem.getInstance().getCurrentPlayer().getStockAccount().buy(id, num)) {
                            MainPage.getInstance().repaint();
                            JOptionPane.showMessageDialog(null, "你已经成功购买"+num+"股"+stock.getName());
                        } else {
                            JOptionPane.showMessageDialog(null, "资金不足！");
                        }
                    } else if (stockOperationIndex == 1) {
                        // 玩家选择卖出
                        int num = Integer.parseInt(JOptionPane.showInputDialog("你想卖出几股？"));
                        if (PlayerSystem.getInstance().getCurrentPlayer().getStockAccount().sell(id, num)) {
                            MainPage.getInstance().repaint();
                            JOptionPane.showMessageDialog(null, "你已经成功卖出"+num+"股"+stock.getName());
                        } else {
                            JOptionPane.showMessageDialog(null, "股票数不足！");
                        }
                    }
                    jFrame.setVisible(false);
                }
            }
        });
        jFrame.add(new JScrollPane(jTable));
        jFrame.pack();
        jFrame.setVisible(true);
    }
}
