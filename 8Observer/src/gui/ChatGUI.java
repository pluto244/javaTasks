package gui;

import chat.Chat;
import chat.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class ChatGUI extends JFrame {
    private Chat chat;
    private DefaultListModel<String> usersListModel;
    private JTextField messageTextField;
    private JTextField usernameTextField;
    private JComboBox<String> directMessageUserComboBox;
    private User currentUser;
    private Map<String, User> userMap;
    private JList<String> messagesList;

    public ChatGUI() {
        chat = new Chat();
        usersListModel = new DefaultListModel<>();
        userMap = new HashMap<>();
        initGUI();
    }

    private void initGUI() {
        setTitle("Chat Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // User list
        JPanel usersPanel = new JPanel();
        usersPanel.setLayout(new BorderLayout());
        JLabel usersLabel = new JLabel("Users:");
        JList<String> usersList = new JList<>(usersListModel);
        usersPanel.add(usersLabel, BorderLayout.NORTH);
        usersPanel.add(new JScrollPane(usersList), BorderLayout.CENTER);
        JButton chooseUserButton = new JButton("Choose User");
        chooseUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedUser = usersList.getSelectedValue();
                if (selectedUser != null) {
                    setCurrentUser(selectedUser);
                }
            }
        });
        usersPanel.add(chooseUserButton, BorderLayout.SOUTH);
        panel.add(usersPanel, BorderLayout.WEST);

        // Message area
        JPanel messagesPanel = new JPanel();
        messagesPanel.setLayout(new BorderLayout());
        JLabel messagesLabel = new JLabel("Chat:");
        messagesList = new JList<>();
        messagesPanel.add(messagesLabel, BorderLayout.NORTH);
        messagesPanel.add(new JScrollPane(messagesList), BorderLayout.CENTER);
        panel.add(messagesPanel, BorderLayout.CENTER);

        // Message input
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        messageTextField = new JTextField();
        messageTextField.setToolTipText("Enter your message here");
        JButton sendButton = new JButton("Send");
        sendButton.setToolTipText("Send message to all users");
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
        inputPanel.add(messageTextField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
        panel.add(inputPanel, BorderLayout.SOUTH);

        // User management
        JPanel userPanel = new JPanel();
        userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.Y_AXIS));

        // Add user panel
        JPanel addUserPanel = new JPanel();
        addUserPanel.setLayout(new BorderLayout());
        usernameTextField = new JTextField();
        usernameTextField.setToolTipText("Enter your username");
        JButton addUserButton = new JButton("Add User");
        addUserButton.setToolTipText("Join the chat");
        addUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addUser();
            }
        });
        addUserPanel.add(usernameTextField, BorderLayout.CENTER);
        addUserPanel.add(addUserButton, BorderLayout.EAST);
        userPanel.add(addUserPanel);

        // Remove user button
        JButton removeUserButton = new JButton("Remove User");
        removeUserButton.setToolTipText("Leave the chat");
        removeUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeUser();
            }
        });
        userPanel.add(removeUserButton);

        // Direct message panel
        JPanel directMessagePanel = new JPanel();
        directMessagePanel.setLayout(new BorderLayout());
        directMessageUserComboBox = new JComboBox<>();
        directMessageUserComboBox.setToolTipText("Select user to send direct message");
        JButton directMessageButton = new JButton("Send Direct Message");
        directMessageButton.setToolTipText("Send private message to selected user");
        directMessageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendDirectMessage();
            }
        });
        directMessagePanel.add(directMessageUserComboBox, BorderLayout.CENTER);
        directMessagePanel.add(directMessageButton, BorderLayout.EAST);
        userPanel.add(directMessagePanel);

        panel.add(userPanel, BorderLayout.EAST);

        add(panel);
    }

    private void setCurrentUser(String username) {
        currentUser = userMap.get(username);
        messagesList.setModel(currentUser.getMessagesModel());
    }

    private void addUser() {
        String username = usernameTextField.getText().trim();
        if (!username.isEmpty() && !userMap.containsKey(username)) {
            User user = new User(username);
            user.joinChat(chat);
            usersListModel.addElement(username);
            directMessageUserComboBox.addItem(username);
            userMap.put(username, user);
            usernameTextField.setText("");
            if (currentUser == null) {
                setCurrentUser(username);
            }
        }
    }

    private void removeUser() {
        if (currentUser != null) {
            String username = currentUser.getName();
            currentUser.leaveChat();
            usersListModel.removeElement(username);
            directMessageUserComboBox.removeItem(username);
            userMap.remove(username);
            currentUser = null;
            if (!userMap.isEmpty()) {
                setCurrentUser(usersListModel.getElementAt(0));
            } else {
                messagesList.setModel(new DefaultListModel<>());
            }
        }
    }

    private void sendMessage() {
        String message = messageTextField.getText().trim();
        if (currentUser != null && !message.isEmpty()) {
            currentUser.sendMessage(message);
            messageTextField.setText("");
        }
    }

    private void sendDirectMessage() {
        String message = messageTextField.getText().trim();
        String toUsername = (String) directMessageUserComboBox.getSelectedItem();
        if (currentUser != null && !message.isEmpty() && toUsername != null) {
            User toUser = userMap.get(toUsername);
            if (toUser != null) {
                currentUser.sendDirectMessage(message, toUser);
                messageTextField.setText("");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ChatGUI().setVisible(true);
            }
        });
    }
}
