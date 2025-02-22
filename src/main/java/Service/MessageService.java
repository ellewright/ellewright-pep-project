package Service;

import java.util.List;
import Model.Message;

import DAO.MessageDAO;

public class MessageService {
    public MessageDAO messageDAO;

    public MessageService() {
        messageDAO = new MessageDAO();
    }

    public MessageService(MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }

    public Message createNewMessage(Message message) {
        return messageDAO.createNewMessage(message);
    }

    public List<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    }

    public Message getMessageById(int message_id) {
        return messageDAO.getMessageById(message_id);
    }

    public Message deleteMessageById(int message_id) {
        return messageDAO.getMessageById(message_id);
    }

    public Message updateMessage(int message_id, Message message) {
        if (messageDAO.getMessageById(message_id) != null) {
            messageDAO.updateMessage(message_id, message);
            return messageDAO.getMessageById(message_id);
        }

        return null;
    }

    public List<Message> getAllMessagesByAccount(int account_id) {
        return messageDAO.getAllMessagesByAccount(account_id);
    }
}
