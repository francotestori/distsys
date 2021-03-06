/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */
package model.wishlist;

@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public interface WishList {
  public static final org.apache.avro.Protocol PROTOCOL = org.apache.avro.Protocol.parse("{\"protocol\":\"WishList\",\"namespace\":\"model.wishlist\",\"types\":[{\"type\":\"record\",\"name\":\"WishListItem\",\"fields\":[{\"name\":\"productId\",\"type\":\"string\"},{\"name\":\"client\",\"type\":\"string\"}]},{\"type\":\"record\",\"name\":\"WishListEvent\",\"fields\":[{\"name\":\"item\",\"type\":\"WishListItem\"},{\"name\":\"type\",\"type\":{\"type\":\"enum\",\"name\":\"Event\",\"symbols\":[\"ADD\",\"REMOVE\"]}},{\"name\":\"timestamp\",\"type\":\"long\"}]}],\"messages\":{\"add\":{\"request\":[{\"name\":\"item\",\"type\":\"WishListItem\"}],\"response\":\"null\"},\"remove\":{\"request\":[{\"name\":\"item\",\"type\":\"WishListItem\"}],\"response\":\"null\"},\"items\":{\"request\":[{\"name\":\"client\",\"type\":\"string\"}],\"response\":{\"type\":\"array\",\"items\":\"WishListItem\"}},\"getAllFrom\":{\"request\":[{\"name\":\"timestamp\",\"type\":\"long\"}],\"response\":{\"type\":\"array\",\"items\":\"WishListItem\"}},\"notifyEvent\":{\"request\":[{\"name\":\"event\",\"type\":\"Event\"}],\"response\":\"null\"}}}");
  java.lang.Void add(model.wishlist.WishListItem item) throws org.apache.avro.AvroRemoteException;
  java.lang.Void remove(model.wishlist.WishListItem item) throws org.apache.avro.AvroRemoteException;
  java.util.List<model.wishlist.WishListItem> items(java.lang.CharSequence client) throws org.apache.avro.AvroRemoteException;
  java.util.List<model.wishlist.WishListItem> getAllFrom(long timestamp) throws org.apache.avro.AvroRemoteException;
  java.lang.Void notifyEvent(model.wishlist.Event event) throws org.apache.avro.AvroRemoteException;

  @SuppressWarnings("all")
  public interface Callback extends WishList {
    public static final org.apache.avro.Protocol PROTOCOL = model.wishlist.WishList.PROTOCOL;
    void add(model.wishlist.WishListItem item, org.apache.avro.ipc.Callback<java.lang.Void> callback) throws java.io.IOException;
    void remove(model.wishlist.WishListItem item, org.apache.avro.ipc.Callback<java.lang.Void> callback) throws java.io.IOException;
    void items(java.lang.CharSequence client, org.apache.avro.ipc.Callback<java.util.List<model.wishlist.WishListItem>> callback) throws java.io.IOException;
    void getAllFrom(long timestamp, org.apache.avro.ipc.Callback<java.util.List<model.wishlist.WishListItem>> callback) throws java.io.IOException;
    void notifyEvent(model.wishlist.Event event, org.apache.avro.ipc.Callback<java.lang.Void> callback) throws java.io.IOException;
  }
}