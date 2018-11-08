import java.util.Random;
import java.util.Scanner;


public class ChatBot3
{

    int emotion = 0;
    public void chatLoop(String statement)
    {
        Scanner in = new Scanner (System.in);
        System.out.println (getGreeting());


        while (!statement.equals("Bye"))
        {


            statement = in.nextLine();
            //getResponse handles the user reply
            System.out.println(getResponse(statement));


        }

    }
    public String getGreeting()
    {
        return "Hi, what is poppin?";
    }

    public String getResponse(String statement)
    {
        String response = "";

        if (statement.length() == 0)
        {
            response = "Say something I'm giving up on you~.";
        }

        else if (findKeyword(statement, "no") >= 0)
        {
            response = "rude";
            emotion--;
        }
        else if (findKeyword(statement, "How's school?") >= 0)
        {
            response = "Hard";
        }
        else if (findKeyword(statement, "I'm tired") >= 0)
        {
            response = "You think you're tired? I have a 1-10 with three lunches for no reason and I have 4 AP classes";
            emotion--;
        }
        else if (findKeyword(statement, "I want to die") >= 0)
        {
            response = "please don't do that";
        }

        else if (findKeyword(statement, "I want to", 0) >= 0)
        {
            response = transformIWantToStatement(statement);
        }
        else if (findKeyword(statement, "I want",0) >= 0)
        {
            response = transformIWantStatement(statement);
        }
        else
        {
            response = getRandomResponse();
        }

        return response;
    }

    private String transformIWantToStatement(String statement)
    {
        //  Remove the final period, if there is one
        statement = statement.trim();
        String lastChar = statement.substring(statement
                .length() - 1);
        if (lastChar.equals("."))
        {
            statement = statement.substring(0, statement
                    .length() - 1);
        }
        int psn = findKeyword (statement, "I want to", 0);
        String restOfStatement = statement.substring(psn + 9).trim();
        return "Why do you want to " + restOfStatement + "?";
    }

    private String transformIWantStatement(String statement)
    {
        statement = statement.trim();
        String lastChar = statement.substring(statement
                .length() - 1);
        if (lastChar.equals("."))
        {
            statement = statement.substring(0, statement
                    .length() - 1);
        }
        int psn = findKeyword (statement, "I want", 0);
        String restOfStatement = statement.substring(psn + 6).trim();
        return "If you want" + restOfStatement + ", then how do you plan to achieve it?";
    }


    private String transformIYouStatement(String statement)
    {
        statement = statement.trim();
        String lastChar = statement.substring(statement
                .length() - 1);
        if (lastChar.equals("."))
        {
            statement = statement.substring(0, statement
                    .length() - 1);
        }

        int psnOfI = findKeyword (statement, "I", 0);
        int psnOfYou = findKeyword (statement, "you", psnOfI);

        String restOfStatement = statement.substring(psnOfI + 1, psnOfYou).trim();
        return "You " + restOfStatement + " me? I could careless";
    }


    private int findKeyword(String statement, String goal,
                            int startPos)
    {
        String phrase = statement.trim().toLowerCase();
        goal = goal.toLowerCase();

        int psn = phrase.indexOf(goal, startPos);

        while (psn >= 0)
        {

            String before = " ", after = " ";
            if (psn > 0)
            {
                before = phrase.substring(psn - 1, psn);
            }
            if (psn + goal.length() < phrase.length())
            {
                after = phrase.substring(
                        psn + goal.length(),
                        psn + goal.length() + 1);
            }

            if (((before.compareTo("a") < 0) || (before
                    .compareTo("z") > 0)) // before is not a
                    // letter
                    && ((after.compareTo("a") < 0) || (after
                    .compareTo("z") > 0)))
            {
                return psn;
            }

            psn = phrase.indexOf(goal, psn + 1);

        }

        return -1;
    }

    private int findKeyword(String statement, String goal)
    {
        return findKeyword (statement, goal, 0);
    }

    private String getRandomResponse ()
    {
        Random r = new Random ();
        if (emotion == 0)
        {
            return randomNeutralResponses [r.nextInt(randomNeutralResponses.length)];
        }
        if (emotion < 0)
        {
            return randomAngryResponses [r.nextInt(randomAngryResponses.length)];
        }
        return randomHappyResponses [r.nextInt(randomHappyResponses.length)];
    }

    private String [] randomNeutralResponses = {"cool, what else",
            "Hmmm.",
            "*fist bumps",
            "I'm good",
            "Alrighty then",
            "tell me something else"
    };
    private String [] randomAngryResponses = {"'Seen'", "'Read'", "No u", "weird flex but ok"};
    private String [] randomHappyResponses = {"Awesome dude,catch ya later", "You're a good person", "Whatever you're struggling with, I wish you best of luck.","You deserve this bread"};

}