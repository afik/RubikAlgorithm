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
    
    
    public void doRotation(){
        
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
