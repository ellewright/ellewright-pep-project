package Controller;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;
import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    MessageService messageService;
    AccountService accountService;

    public SocialMediaController() {
        this.messageService = new MessageService();
        this.accountService = new AccountService();
    }

    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();

        app.get("example-endpoint", this::exampleHandler);
        app.post("register", this::createNewUserHandler); // 1
        // app.post("login", this::loginUserHandler); // 2
        // app.post("messages", this::createNewMessage); // 3
        app.get("messages", this::getAllMessagesHandler); // 4
        app.get("messages/{message_id}", this::getMessageByIdHandler); // 5
        // app.delete("messages/{message_id}", this::deleteMessageById); // 6
        // app.patch("messages/{message_id}", this::updateMessage); // 7
        app.get("accounts/{account_id}/messages", this::getAllMessagesByAccountHandler); // 8

        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void exampleHandler(Context context) {
        context.json("sample text");
    }

    private void createNewUserHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body(), Account.class);
        Account createdAccount = accountService.createAccount(account);

        if (createdAccount == null) {
            ctx.status(400);
        } else {
            ctx.json(mapper.writeValueAsString(createdAccount));
        }
    }

    private void getAllMessagesHandler(Context ctx) {
        List<Message> messages = messageService.getAllMessages();
        ctx.json(messages);
    }

    private void getMessageByIdHandler(Context ctx) {
        Message message = messageService.getMessageById(Integer.parseInt(ctx.pathParam("message_id")));
        ctx.json(message);
    }

    private void getAllMessagesByAccountHandler(Context ctx) {
        List<Message> messages = messageService.getAllMessagesByAccount(Integer.parseInt(ctx.pathParam("account_id")));
        ctx.json(messages);
    }
}