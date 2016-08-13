package tw.bir.agrimessenger.service.CallbackInterface;

import tw.bir.agrimessenger.service.AgricultureMessage;

/**
 * Created by puyan on 8/13/16.
 */
public interface MessageListCallback {
    void notifyList(AgricultureMessage[] messageList, String msg);
}
