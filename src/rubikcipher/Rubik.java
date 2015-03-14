/*
 * This code is implementation of our paper's at : 
 * Simulating permutation of Rubik's cube
 */

package rubikcipher;


/**
 *
 * @author Khoirunnisa Afifah (k.afis3@rocketmail.com)
 *         Yollanda Sekarrini 
 */
public class Rubik {
    private char elmt[][][]; //element rubik[face][row][column], will be filled by 0/1
                             //face number start from 0: up, left, front, right, back, down
    
    /**
     * Perform rotation based on internal key
     * @param iKey is internal key used in Fiestel
     *        from each char of this string, permutation code
     *        compute using getCode function
     */
    public void doRotation(String iKey){
        for(int i=0; i<iKey.length(); i++){
            switch(getCode(iKey.charAt(i))) {
                case 0:
                    rotateR();
                    break;
                case 1:
                    rotateRi();
                    break;
                case 2:
                    rotateL();
                    break;
                case 3:
                    rotateLi();
                    break;
                case 4:
                    rotateU();
                    break;
                case 5:
                    rotateUi();
                    break;
                case 6:
                    rotateD();
                    break;
                case 7:
                    rotateDi();
                    break;
                case 8:
                    rotateF();
                    break;
                case 9:
                    rotateFi();
                    break;
                case 10:
                    rotateB();
                    break;
                case 11:
                    rotateBi();
                    break;
                case 12:
                    rotateM();
                    break;
                case 13:
                    rotateMi();
                    break;
                case 14:
                    rotateE();
                    break;
                case 15:
                    rotateEi();
                    break;
            }
        }
    }
    
    /**
     * @param ch is character used to get the code
     * @return value of four LSB of ch
     *         value is number between 0 to 15 in permuation matriks
     */
    public int getCode(char ch){
        String binary = Integer.toBinaryString(ch);
        binary = binary.substring(4);        
        return Integer.parseInt(binary);
    }
    
    /**
     * Return all Bit from face 0 to face 5
     * @return All bit in BinaryString
     */
    public String readAllBit() {
        String retval="";
        for (int face=0; face<6; face++) {
            for (int row=0; row<3; row++) {
                for (int col=0; col<3; col++) {
                    retval+=elmt[face][row][col];
                }
            }
        }
        return retval;
    }
    
    /**
     * Fill the cube with binaryString
     * Size of binaryString should be 48 or only 48 bit will be processed
     * @param binaryString
     */
    public void putAllBit(String binaryString){
        int idx=0;
        for (int face=0; face<6; face++) {
            for (int row=0; row<3; row++) {
                for (int col=0; col<3; col++) {
                    elmt[face][row][col] = binaryString.charAt(idx);
                    idx++;
                }
            }
        } 
    }
    
    /**
     * @param face number of face
     * @param row number
     * @param col number
     * @return 
     */
    public char getElement(int face, int row, int col) {
        return elmt[face][row][col];
    }
    
    /**
     * @param face number of face
     * @param row number
     * @param col number
     * @param value 
     */
    public void setElement(int face, int row, int col, char value) {
        elmt[face][row][col] = value;
    }
    
    /**
     * SIMULATING ALL 16 ROTATION OF RUBIK'S CUBE
     */
    public void rotateR(){
        
    }
    
    public void rotateRi(){
        
    }
    
    public void rotateL(){
        
    }
    
    public void rotateLi(){
        
    }
    
    public void rotateF(){
        
    }
    
    public void rotateFi(){
        
    }
    
    public void rotateB(){
        
    }
    
    public void rotateBi(){
        
    }
    
    public void rotateU(){
        
    }
    
    public void rotateUi(){
        
    }
    
    public void rotateD(){
        
    }

    public void rotateDi(){
        
    }

    public void rotateE(){
        
    }
    
    public void rotateEi(){
        
    }
    
    public void rotateM(){
        
    }
    
    public void rotateMi(){
        
    }
    
}
