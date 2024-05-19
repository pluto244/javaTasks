import chat.Chat;
import chat.User;

public class ChatDemo {
    public static void main(String[] args) {
        Chat chat = new Chat();

        User user1 = new User("User1");
        User user2 = new User("User2");
        User user3 = new User("User3");
        User user4 = new User("User4");
        User user5 = new User("User5");

        user1.joinChat(chat);
        user2.joinChat(chat);
        user3.joinChat(chat);
        user4.joinChat(chat);
        user5.joinChat(chat);

        user1.sendMessage("Hello everyone!");
        user2.sendMessage("Hi User1!");
        user3.sendMessage("Good morning!");
        user4.sendMessage("Hey!");

        user5.sendDirectMessage("How are you?", user1);

        user3.leaveChat();
        user1.sendMessage("User3 left the chat.");

        user1.sendDirectMessage("Let's catch up later.", user2);
    }
}
