package action;

import card.Card;
import player.PlayerSystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CheckCardPacketAction implements IAction {
    @Override
    public void trigger() {
        JFrame jFrame = new JFrame();
        JTable jTable;
        String[] columnHeader = {"卡片名"};
        int cardNum = PlayerSystem.getInstance().getCurrentPlayer().getCardPacket().getCardList().size();
        String[][] rowData = new String[cardNum][1];
        for (int i = 0; i < cardNum; i++) {
            rowData[i][0] = PlayerSystem.getInstance().getCurrentPlayer().getCardPacket().getCardList().get(i).getName();
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
                    Card card = PlayerSystem.getInstance().getCurrentPlayer().getCardPacket().getCardList().get(id);
                    if (card.use(PlayerSystem.getInstance().getCurrentPlayer())) {
                        PlayerSystem.getInstance().getCurrentPlayer().getCardPacket().getCardList().remove(card);
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
