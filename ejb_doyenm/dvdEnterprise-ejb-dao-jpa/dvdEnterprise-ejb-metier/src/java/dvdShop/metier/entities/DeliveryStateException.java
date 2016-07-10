/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dvdShop.metier.entities;

/**
 *
 * @author doyenm
 */
public class DeliveryStateException extends Exception {

    private static final long serialVersionUID = 3222567124933084522L;

    public DeliveryStateException(String message) {
        super(message);
    }
}
