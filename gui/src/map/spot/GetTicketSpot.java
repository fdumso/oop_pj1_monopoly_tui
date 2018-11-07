package map.spot;

import gui.MainPage;
import player.Player;

import javax.swing.*;

public class GetTicketSpot extends Spot {
    public GetTicketSpot(String name, int x, int y, int h, int w) {
        super(name, Type.GET_TICKET, x, y, h, w);
    }

    @Override
    public void stay(Player player) {
        int ticketNum = (int) (Math.random() * 16);
        player.getWallet().addTicket(ticketNum);
        MainPage.getInstance().repaint();
        JOptionPane.showMessageDialog(null, "恭喜您！您获得了" + ticketNum + "张点券！", "赠送点券点：" + this.getName(),
                JOptionPane.INFORMATION_MESSAGE, null);
    }
}
