package service_example;

import model.wishlist.WishList;
import model.wishlist.WishListEvent;
import model.wishlist.WishListItem;
import org.apache.avro.ipc.NettyTransceiver;
import org.apache.avro.ipc.specific.SpecificRequestor;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Created by franco on 06/11/15.
 */
public class Client {

    public static void main(String[] args) throws IOException {

        NettyTransceiver client = new NettyTransceiver(new InetSocketAddress("192.168.99.100", 65111));

        WishList proxy = SpecificRequestor.getClient(WishList.class, client);

        WishListItem item1 = new WishListItem();
        WishListItem item2 = new WishListItem();
        WishListItem item3 = new WishListItem();

        item1.setProductId("Xperia U");
        item2.setProductId("Moto G");
        item3.setProductId("Iphone 6s");

        item1.setClient(client.getRemoteName());
        item2.setClient(client.getRemoteName());
        item3.setClient(client.getRemoteName());


        proxy.add(item1);
        proxy.add(item2);
        proxy.remove(item1);
        proxy.add(item3);

        System.out.println("El cliente añadio a su carrito los items:" + "\n");
        for(WishListItem item : proxy.items(client.getRemoteName())){
            System.out.println(item.getProductId() +"\n");
        }

        // cleanup
        client.close();

    }
}
