package service_example;

import model.wishlist.Event;
import model.wishlist.WishList;
import model.wishlist.WishListEvent;
import model.wishlist.WishListItem;
import org.apache.avro.AvroRemoteException;
import org.apache.avro.ipc.NettyServer;
import org.apache.avro.ipc.specific.SpecificResponder;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by franco on 06/11/15.
 */
public class Server {

    public static class WishListImpl implements WishList{

        private List<WishListEvent> items = new ArrayList<WishListEvent>();

        @Override
        public Void add(WishListItem item) throws AvroRemoteException {
            WishListEvent event = new WishListEvent();
            event.setItem(item);
            event.setTimestamp(System.currentTimeMillis());
            event.setType(Event.ADD);
            notifyEvent(event.getType());
            return null;
        }

        @Override
        public Void remove(WishListItem item) throws AvroRemoteException {
            WishListEvent event = new WishListEvent();
            event.setItem(item);
            event.setTimestamp(System.currentTimeMillis());
            event.setType(Event.REMOVE);
            notifyEvent(event.getType());
            return null;
        }

        @Override
        public List<WishListItem> items(CharSequence client) throws AvroRemoteException {
            List<WishListItem> result = new ArrayList<WishListItem>();
            for (WishListEvent item : items) {
                if(client.equals(item.getItem().getClient())) result.add(item.getItem());
            }
            return result;
        }

        @Override
        public List<WishListItem> getAllFrom(long timestamp) throws AvroRemoteException {
            List<WishListItem> result = new ArrayList<WishListItem>();
            for (WishListEvent item : items) {
                if(timestamp == item.getTimestamp()) result.add(item.getItem());
            }
            return result;
        }

        @Override
        public Void notifyEvent(Event event) throws AvroRemoteException {
            if(event.equals(Event.ADD)) System.out.println("Se agrego un item");
            if(event.equals(Event.REMOVE)) System.out.println("Se removio un item");
            return null;
        }
    }

    public static void main(String[] args) throws IOException {

        SpecificResponder responder = new SpecificResponder(WishList.class, new WishListImpl());

        NettyServer server = new NettyServer(responder, new InetSocketAddress(65111));
        server.start();

        System.out.println("Server started");

    }
}
