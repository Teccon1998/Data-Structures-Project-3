import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;
import java.lang.Character;
import java.lang.String;




public class StackProgram {
    public static void main(String[] args) throws Exception {
        Scanner keyboard = new Scanner(System.in);
        LinkedList<String> ll = new LinkedList<String>();

        displayMenu(keyboard, ll);
        keyboard.close();
    }
    static int displayMenu(Scanner keyboard,LinkedList<String> ll)
    {
        System.out.println("Please select what type of conversion you would like to do:");
        System.out.println("1) Infix to postfix");
        System.out.println("2) Postfix to infix");
        System.out.println("3) Print Equations");
        System.out.println("4) Exit");

        int choice = keyboard.nextInt();
        keyboard.nextLine();
        if(choice != 1 && choice != 2 && choice != 3 && choice != 4 )
        {
            System.out.println("Invalid menu option. Please select valid menu option");
            displayMenu(keyboard,ll);
        }
        else 
        {
            switch(choice)
            {
                case 1: 
                    inToPost(keyboard, ll);
                    displayMenu(keyboard,ll);
                    break;
                case 2:
                    postToInf(keyboard,ll);
                    displayMenu(keyboard,ll);
                    break;
                case 3:
                    printEquations(ll);
                    displayMenu(keyboard,ll);
                    break;
                case 4:
                    printEquations(ll);
                    break;
            }
        }
        return 0;
    }

    static String inToPost(Scanner keyboard, LinkedList<String> ll)
    {
        System.out.print("Type in infix equation here: ");
        
        String infix = keyboard.nextLine();
        infix.replaceAll("\\s", "");
        ll.add(infix);
        String postFix = "";
        String errorMsg = "ERROR IN EQUATION RETRY FROM MENU.";
        Stack<Character> stack = new Stack<Character>();
        
        for(int i = 0; i < infix.length(); i++)
        {
            
            if(isOperand(infix.charAt(i)))
            {
                postFix += infix.charAt(i);
            }
            else if(isOperator(infix.charAt(i)))
            {
                if(precedence(infix.charAt(i), stack)>0)
                {
                    while(stack.isEmpty() == false && precedence(stack.peek(),stack) >= precedence(infix.charAt(i), stack))
                    {
                        postFix += stack.pop();
                    }
                    stack.push(infix.charAt(i));
                }
                else if(infix.charAt(i) == ')')
                {
                    char x = stack.pop();
                    while(x!= '(')
                    {
                        postFix += x;
                        x = stack.pop();
                    }
                }
                else if(infix.charAt(i) == '(')
                {
                    stack.push(infix.charAt(i));
                }
                else 
                {
                    postFix += infix.charAt(i);
                }
               
            }
            else 
            {
                return errorMsg;
            }
            
        }

        if(stack.isEmpty() == false)
        {
            for(int i = 0; i < stack.size(); i++)
            {
                postFix += stack.pop();
            }
        }

        System.out.println("Your converted equation: " + postFix);
        return postFix;
    }
    static String postToInf(Scanner keyboard, LinkedList<String> ll)
    {
        System.out.print("Type in postfix equation here: ");

        String postFix = keyboard.nextLine();
        postFix = postFix.replaceAll("\\s+", "");
        ll.add(postFix);
        Stack<String> stack = new Stack<String>();

        for(int i =0; i< postFix.length(); i++)
        {
            if(isOperand(postFix.charAt(i)))
            {
                stack.push(postFix.charAt(i) + "");
            }
            else 
            {
                String op1 = stack.peek();
                stack.pop();
                String op2 = stack.peek();
                stack.pop();
                stack.push("(" + op2 +postFix.charAt(i) + op1 + ")");
            }
        }

        System.out.println("Your converted equation: " + stack.peek());
        return stack.peek();
    }
    static void printEquations(LinkedList<String> ll)
    {
        for(int i = 0; i<ll.size(); i++)
        {
            System.out.print(i+1 + ") ");
            System.out.println(ll.get(i));
            System.out.println();
        }
    }
    static int precedence(char Char, Stack<Character> stack)
    {
       switch(Char)
       {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
       }
       return -1;
    }
    static boolean isOperator(char Character)
    {
        if(Character == '+' || Character == '-' || Character == '*' || Character == '/' || Character == '(' || Character == ')' ||  Character == '^')
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    static boolean isOperand(char Character)
    {  
        if((Character >= 'a' && Character <= 'z') || (Character >= 'A' && Character <= 'Z') ||(Character >= '0' && Character <= '9'))
        { //alexander Crowe watermark
            return true;
        }
        else
        {
            return false;
        }
    }
    static int charVal(char character)
    {
        if(character == '-')
        {
            return 0;

        }
        else if(character == '+')
        {
            return 1;
        }
        else if(character == '/')
        {
            return 2;
        }
        else if(character == '*')
        {
            return 3;
        }
        else if(character == '^')
        {
            return 4;
        }
        else  
        {
            return -1;
        }
    }
}

/*
Please select what type of conversion you would like to do:
1)	Infix to postfix
2)	Postfix to infix
3)	Print Equations
4)	Exit
*/