/*
 * This code is implementation of our paper's at : 
 */

package rubikcipher;


/**
 *
 * @author Khoirunnisa Afifah (k.afis3@rocketmail.com)
 *         Yollanda Sekarrini 
 */
public class RubikCipher {
    private String inputBlock; //input teks : 12 char
    private String inputBinary; //input teks in BinaryString
    private String extKey;  //key from user
    private int numIteration; //length of extKey
    private String outBlock; //output teks
    private String outBinary; //output teks in BinaryString
    
    private String[] L; //left part String in fiestel
    private String[] R; //right part String in fiestel
    private String[] key; //internal key

    public Rubik rubik = new Rubik();
    
    /**
     * MAIN
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String input = "HaiApaKabar?";
        String key = "yola";
        RubikCipher rc = new RubikCipher();
        rc.setValidInput(input);
        System.out.println(rc.getInputBinary() + " " + rc.getInputBlock());
        rc.prepareKey(key);
        for (int i = 0; i < rc.numIteration; i++) {
            System.out.println(rc.key[i]);
        }
        rc.doFeistel();
        System.out.println(rc.getOutBinary() + " " + rc.getOutBlock());
        rc.reverseFeistel();
        System.out.println(rc.getInputBinary() + " " + rc.getInputBlock());
    }
    
    
    /** 
     * Set InputBlock
     * @return 
     * If size of input > 12 character return -1 (error)
     * If size of input = 12 InpurBlock set as input, return 1
     * If size of input < 12 append 0 until length equals 12, return 1
     */
    public int setValidInput(String input) {
        int retVal=-1;
        if (input.length() == 12) {
            inputBlock = input;
            inputBinary = toBinary(inputBlock);
            retVal = 1;
        }
        else if (input.length()>12) {
            retVal = -1;
        }
        else if (input.length()<12) {
            String zero="";
            for (int i=0; i<12-input.length();i++) {
                zero += "0";
            }
            inputBlock = input+zero;
            inputBinary = toBinary(inputBlock);
            retVal = 1;
        }
        return retVal;
    }
    
    /**
     * Set numIteration, internal key, and external key
     * length of key must > 1
     * @return 1 if success, -1 if failed
     */
    public int prepareKey(String eKey) {
        int retVal=-1;
        if (eKey.length() > 1) {
            key = new String[eKey.length()];
            String temp = eKey.toLowerCase();
            String res;
            for (int j=0; j<eKey.length();j++){
                res = "";
                for (int i=0; i<temp.length();i++) {
                    res += (char) ((temp.charAt(i) + 4 - (int)'a') % 25 + (int)'a');
                }
                key[j] = res;
                temp = res;
            }

            numIteration = eKey.length();           extKey = eKey.toLowerCase();
            retVal = 1;

        }
        else {
            retVal = -1;
        }
        return retVal;
    }
    
    /**
     * should be called after setValidInput and prepareKey
     * processed in String of bit
     */
    public void doFeistel(){
        String rubikResult;
        L = new String[numIteration+1];
        R = new String[numIteration+1];
        
        L[0] = inputBinary.substring(0, 48);
        R[0] = inputBinary.substring(48, 96);
        
        for (int i=1; i<numIteration+1; i++) {
            L[i] = R[i-1];
            //DO RUBIK PERMUTATION
            rubik.putAllBit(R[i-1]);
            rubik.doRotation(key[i-1]);
            rubikResult = rubik.readAllBit();
            R[i] = XOR(L[i-1], rubikResult);
            System.out.println(i + " " + L[i] + " " + R[i]);
        }
        
        outBinary = L[numIteration] + R[numIteration];
        outBlock = fromBinary(outBinary);
    }
    
    /**
     * used for decrypt the message
     * perform Feistel from down to top
     * using reverseRotation of Rubik
     */
    public void reverseFeistel(){
        String rubikResult;
        L = new String[numIteration+1];
        R = new String[numIteration+1];
        
        L[numIteration] = outBinary.substring(0, 48);
        R[numIteration] = outBinary.substring(48, 96);
        
        for (int i=numIteration; i>0; i--) {
            R[i-1] = L[i];
            //DO RUBIK PERMUTATION
            rubik.putAllBit(L[i]);
            rubik.doRotation(key[i-1]);
            rubikResult = rubik.readAllBit();
            L[i-1] = XOR(R[i], rubikResult);
            System.out.println(i + " " + L[i] + " " + R[i]);
        }
        
        inputBinary = L[0] + R[0];
        inputBlock = fromBinary(inputBinary);
    }
    
    /**
     * Convert text to binaryString
     * Each character parsed into 8 binary char
     */
    public String toBinary(String teks) {
        String binary="";
        String perChar;
        for (int i=0; i<teks.length(); i++){
            perChar = Integer.toBinaryString(teks.charAt(i));
            if (perChar.length()<8) {
                String zero="";
                for (int j=0; j<8-perChar.length();j++) {
                    zero += "0";
                }
                perChar = zero+perChar;
                //System.out.println(perChar);
            }
            binary += perChar;
        }
        return binary;
    }
    
    /**
     * Convert binary to normal string
     */
    public String fromBinary(String binary){
        String retval ="";
        int ascii;
        String bitProcess;
        while(binary.length() % 8 != 0){
            binary = '0' + binary;
        }
        while(binary.length()>0){
            bitProcess = binary.substring(0,8);
            ascii = Integer.parseInt(bitProcess,2);
            retval += (char)ascii;
            binary = binary.substring(8);
        }
        return retval;
    }
    
    /**
     * XOR operation on Binary String
     * @param left and right are binary string with same size
     */
    public String XOR(String left, String right){
        String retVal="";
        int ch;
        
        if (left.length()==right.length()) {
            for (int i = 0; i<left.length(); i++) {
                if (left.charAt(i) == right.charAt(i)) {
                    ch = 0;
                }
                else {
                    ch = 1;
                }
                retVal += ch;
            }
        }
        
        return retVal;
    }
    /// GETTER AND SETTER
    
    /**
     * @return the inputBlock
     */
    public String getInputBlock() {
        return inputBlock;
    }

    /**
     * @return the extKey
     */
    public String getExtKey() {
        return extKey;
    }

    /**
     * @param extKey the extKey to set
     */
    public void setExtKey(String extKey) {
        this.extKey = extKey;
    }

    /**
     * @return the numIteration
     */
    public int getNumIteration() {
        return numIteration;
    }

    /**
     * @return the outBlock
     */
    public String getOutBlock() {
        return outBlock;
    }

    /**
     * @return the inputBinary
     */
    public String getInputBinary() {
        return inputBinary;
    }

    /**
     * @return the outBinary
     */
    public String getOutBinary() {
        return outBinary;
    }
    
}
