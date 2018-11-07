package action;

import player.Player;
import player.PlayerSystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CheckPlayersInfoAction implements IAction {
    @Override
    public void trigger() {
        JFrame jFrame = new JFrame();
        JTable jTable;
        String[] columnHeader = {"玩家名字", "现金", "存款", "房产数", "点券", "总资产"};
        int playerNum = PlayerSystem.getInstance().getPlayerNumber();
        String[][] rowData = new String[playerNum][6];
        for (int i = 0; i < playerNum; i++) {
            Player player = PlayerSystem.getInstance().getPlayerList().get(i);
            rowData[i][0] = player.getName();
            rowData[i][1] = String.format("%.2f", player.getWallet().getCash());
            rowData[i][2] = String.format("%.2f", player.getBankAccount().getDeposit());
            rowData[i][3] = String.format("%d", player.getEstate().getHouseNum());
            rowData[i][4] = String.format("%d", player.getWallet().getTicket());
            rowData[i][5] = String.format("%.2f", player.getCapital());
        }
        jTable = new JTable(new DefaultTableModel(rowData, columnHeader) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        jFrame.add(new JScrollPane(jTable));
        jFrame.pack();
        jFrame.setVisible(true);
    }
}
