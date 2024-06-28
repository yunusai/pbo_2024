/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tugasteori12;

/**
 *
 * @author asus
 */
private class PrintingTask extends SwingWorker<Object, Object> { 
    private final MessageFormat headerFormat; 
    private final MessageFormat footerFormat; 
    private final boolean interactive; 
    private volatile boolean complete = false; 
    private volatile String message; 
    
    public PrintingTask(MessageFormat header, MessageFormat footer, 
        boolean interactive) { 
        this.headerFormat = header; 
        this.footerFormat = footer; 
        this.interactive = interactive; 
    } 
    
    @Override 
    protected Object doInBackground() { 
        try { 
        complete = text.print(headerFormat, footerFormat, 
        true, null, null, interactive); 
        message = "Printing " + (complete ? "complete" : 
       "canceled"); 
        } catch (PrinterException ex) { 
        message = "Sorry, a printer error occurred"; 
        } catch (SecurityException ex) { 
        message = 
        "Sorry, cannot access the printer due to security reasons";
       } 
        return null; 
        } 
        @Override 
        protected void done() { 
        message(!complete, message); 
        } 
    } 