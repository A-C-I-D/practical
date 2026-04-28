import java.util.*;

public class Chatbot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        HashMap<String, List<String>> responses = new HashMap<>();

        responses.put("hello", Arrays.asList(
                "Hello! How can I assist you today?",
                "Hey there! What can I do for you?",
                "Hi! Need any help?",
                "Hello! I'm here to answer your questions or just chat.",
                "Hi! Tell me what you'd like to talk about."));

        responses.put("hi", Arrays.asList(
                "Hi there!",
                "Hello! Can I help?",
                "Hey! What's up?",
                "Hi! It's great to chat with you.",
                "Hello! How are you doing today?"));

        responses.put("how are you", Arrays.asList(
                "I'm a chatbot, so I don't have feelings, but I'm ready to help!",
                "I am doing great in code form! What can I do for you?",
                "I'm here and fully operational. How can I assist you today?"));

        responses.put("thanks", Arrays.asList(
                "You're welcome!",
                "No problem, happy to help!",
                "Glad I could assist!",
                "Anytime! Let me know if you need anything else."));

        responses.put("help", Arrays.asList(
                "I can answer simple questions and respond to greetings. Try saying 'hello', 'bye', or 'thanks'.",
                "Need help? Just ask me a question or say 'hello' to start the conversation.",
                "I am here to help. What would you like to know or discuss?"));

        responses.put("bye", Arrays.asList(
                "Goodbye!",
                "See you soon!",
                "Take care!",
                "Bye! Have a great day!",
                "Farewell! Come back if you have more questions."));

        System.out.println("Chatbot: Hello! Type 'bye' to exit.");

        while (true) {
            System.out.print("You: ");
            String userInput = scanner.nextLine().toLowerCase();
            boolean responseFound = false;

            for (String key : responses.keySet()) {
                if (userInput.contains(key)) {
                    List<String> replyList = responses.get(key);
                    String reply = replyList.get(random.nextInt(replyList.size()));

                    System.out.println("Chatbot: " + reply);

                    if (key.equals("bye")) {
                        scanner.close();
                        return;
                    }

                    responseFound = true;
                    break;
                }
            }

            if (!responseFound) {
                System.out.println("Chatbot: Hmm I didn't get that.");
            }
        }
    }
}