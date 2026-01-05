import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class unCRACKABLE
{


    private static final int EXCESS = 32;
    private static final int NUMBER_chars = 95;

    private static int[] stringToASCII(String plainText) {
        int length = plainText.length();
        int[] plainASCII = new int[length];
        for (int i = 0; i < length; i++) {
            plainASCII[i] = plainText.charAt(i);
        }
        return plainASCII;
    }

    private static void encrypt(int[] encryptKey, File inputFile, File outputFile) {
        try {
            Scanner inputFileReader = new Scanner(inputFile);
            inputFileReader.useDelimiter("");
            FileWriter reset = new FileWriter(outputFile);
            reset.write("");
            reset.close();
            BufferedWriter outputFileWriter = new BufferedWriter(new FileWriter(outputFile, true));

            int currentOutputCharIndex = 0;
            while (inputFileReader.hasNext()) {
                char currentInputChar = inputFileReader.next().charAt(0);
                char currentOutputChar;

                if (currentInputChar != 13 && currentInputChar != 10) {
                    int currentKeyAscii = encryptKey[(currentOutputCharIndex % encryptKey.length)];
                    currentOutputChar = (char) ((currentInputChar - EXCESS + currentKeyAscii - EXCESS) % NUMBER_chars + EXCESS);
                    currentOutputCharIndex++;
                    outputFileWriter.write(currentOutputChar);
                } else if (currentInputChar == 13) {
                    outputFileWriter.newLine();
                }
            }
            outputFileWriter.close();

        } catch (IOException e) {
            System.out.println("\nERROR: could not decrypt file");
        }
        System.out.println("\nFile has been encrypted");
    }

    private static void decrypt(int[] decryptKey, File inputFile, File outputFile) {
        try {
            Scanner inputFileReader = new Scanner(inputFile);
            inputFileReader.useDelimiter("");

            FileWriter reset = new FileWriter(outputFile);
            reset.write("");
            reset.close();

            BufferedWriter outputFileWriter = new BufferedWriter(new FileWriter(outputFile, true));

            int currentOutputCharIndex = 0;
            while (inputFileReader.hasNext()) {
                char currentInputChar = inputFileReader.next().charAt(0);
                char currentOutputChar;

                if (currentInputChar != 13 && currentInputChar != 10) {
                    int currentKeyAscii = decryptKey[(currentOutputCharIndex % decryptKey.length)];
                    currentOutputChar = (char) ((currentInputChar - currentKeyAscii + NUMBER_chars) % NUMBER_chars + EXCESS);
                    currentOutputCharIndex++;
                    outputFileWriter.write(currentOutputChar);
                } else if (currentInputChar == 13) {
                    outputFileWriter.newLine();
                }
            }
            outputFileWriter.close();

        } catch (IOException e) {
            System.out.println("\nERROR: could not decrypt file");
        }
        System.out.println("\nFile has been decrypted");
    }

    private static void helpPage() {
        System.out.println("\n----------------------------------------------------------------------------------------");
        System.out.println("This is the help-page of unCRACKABLE");
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("uncrackable can encrypt and decrypt files using the caesar- or viginere-cypher");
        System.out.println("some examples for the usage of unCRACKABLE are:");
        System.out.println();
        System.out.println("uncrackable -e -p myPassword -i myClearInput.txt -o myCypherOutput.txt");
        System.out.println("uncrackable -d -p myPassword -i myCypherInput.txt -o myClearOutput.txt");
        System.out.println("uncrackable -d -p myPassword");
        System.out.println("uncrackable -e -p myPassword -i myNamedInput.txt");
        System.out.println("----------------------------------------------------------------------------------------");

        System.out.println("explanation of each argument:");
        System.out.println();
        System.out.println("-e or -encrypt: if you want to encrypt a file");
        System.out.println("-d or -decrypt: if you want to decrypt a file");
        System.out.println("one argument -e or -d is required, if both are used the later one overwrites");
        System.out.println("-p or -password: this is the password used --> required");
        System.out.println("-i or -input: is the input file, if not set, input.txt is used as input");
        System.out.println("-o or -output: is the output file, if not set, output.txt is used as output");
        System.out.println("-h or -help: this page, overwrites all other arguments");
        System.out.println("----------------------------------------------------------------------------------------");
    }

    public static void main(String[] args) {

        String mode = null;
        String password = null;

        String inputPath = null;
        String outputPath = null;

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-e", "-encrypt":
                    mode = "encrypt";
                    break;
                case "-d", "-decrypt":
                    mode = "decrypt";
                    break;
                case "-h", "-help":
                    helpPage();
                    System.exit(0);
                    break;
                case "-p", "-password":
                    if (args.length - i > 1) {
                        password = args[++i];
                        break;
                    }
                case "-i", "-input":
                    if (args.length - i > 1) {
                        inputPath = args[++i];
                        break;
                    }
                case "-o", "-output":
                    if (args.length - i > 1) {
                        outputPath = args[++i];
                        break;
                    }
                default:
                    System.out.println("\nERROR: invalid command");
                    System.exit(0);
            }
        }
        if (mode != null && password != null) {
            File inputFile = new File("input.txt");
            File outputFile = new File("output.txt");
            if (inputPath != null) {
                inputFile = new File(inputPath);
            }
            if (outputPath != null) {
                outputFile = new File(outputPath);
            }
            int[] passwordArray = stringToASCII(password);
            if (mode.equals("encrypt")) {
                encrypt(passwordArray, inputFile, outputFile);
            } else if (mode.equals("decrypt")) {
                decrypt(passwordArray, inputFile, outputFile);
            } else {
                System.out.println("\nERROR: invalid command");
            }
        } else if (mode == null && password == null && inputPath == null && outputPath == null) {
            helpPage();
        } else {
            System.out.println("\nERROR: invalid command");
        }
    }
}










