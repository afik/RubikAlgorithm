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

    
    /**
     * MAIN
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String input = "HaiApaKabar?";
        String key = "yola";
        RubikCipher rc = new RubikCipher();
        System.out.println(rc.setValidInput(input) + " " + rc.getInputBlock() + " " + rc.getInputBinary());
        rc.prepareKey(key);
        rc.doFeistel();
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

            numIteration = eKey.length();
            extKey = eKey.toLowerCase();
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
        L = new String[numIteration];
        R = new String[numIteration];
        
        L[0] = inputBinary.substring(0, 48);
        R[0] = inputBinary.substring(48, 96);
        
        for (int i=1; i<numIteration-1; i++) {
            L[i+1] = R[0];
            //DO RUBIK PERMUTATION
            rubikResult = R[0];
            R[i+1] = XOR(L[i], rubikResult);
        }
        
    }
    
    /**
     * Return binary value of teks
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
