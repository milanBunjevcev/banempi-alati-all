/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.bane.alati.updater;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.util.jar.Attributes;
import java.util.jar.Manifest;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Milan
 */
public class Update {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        JOptionPane.showMessageDialog(null, "Pritisnite OK da zapocnete azuriranje.");
        Thread.sleep(1000);
        final File folder = new File("//banesombor/razmena/INSTALACIJE/Alati");
        String fn = "temp";
        File f = new File(fn);
        String pathF = f.getAbsolutePath();
        String pathFParent = f.getAbsolutePath().substring(0, pathF.length() - (fn.length() + 1));
        //JOptionPane.showMessageDialog(null, pathFParent);

        File f2 = new File(pathFParent);
        String pathF2 = f2.getAbsolutePath();
        String pathF2Parent = f2.getAbsolutePath().substring(0, pathF2.length() - (f2.getName().length() + 1));
        //JOptionPane.showMessageDialog(null, pathF2);

        listFilesForFolder(folder, pathF2);
        JOptionPane.showMessageDialog(null, "Ažuriranje je završeno.\n"
                + "Program æe se sada startovati.");
        Process proc = Runtime.getRuntime().exec("java -jar " + pathF2 + "/Alati.jar");
    }

    public static void copyAllFromPath(String path) {
        String p2 = "//banesombor/razmena/INSTALACIJE/Alati";
        listFilesForFolder(new File(p2), "fajlovi");
    }

    static void listFilesForFolder(final File folder, String path) {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                try {
                    File f = new File(path + "/" + fileEntry.getName());
                    if (!f.exists()) {
                        copyFile(fileEntry, f);
                    }
                    listFilesForFolder(fileEntry, path + "/" + fileEntry.getName());
                } catch (IOException ex) {
                    Logger.getLogger(Update.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    File f = new File(path + "/" + fileEntry.getName());
                    if (f.exists()) {
                        if (!f.getName().equalsIgnoreCase("updater.jar")) {
                            f.delete();
                            copyFile(fileEntry, f);
                        }
                    } else {
                        copyFile(fileEntry, f);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Update.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    static void copyFile(File source, File dest) throws IOException {
        Files.copy(source.toPath(), dest.toPath());
        //JOptionPane.showMessageDialog(null, source.getAbsolutePath() + "\nu\n"
        //        + dest.getAbsolutePath());
    }

    public static boolean imaLiUpdate(Class theClass) throws IOException {
        try {
            String currV = getThisVersion(theClass);
            //JOptionPane.showMessageDialog(null, currV);
            String lastV = getLatestVersion();
            //JOptionPane.showMessageDialog(null, lastV);
            double c = Double.parseDouble(currV);
            double l = Double.parseDouble(lastV);
            if (l > c) {
                JOptionPane.showMessageDialog(null, "Dostupna je nova verzija programa.\n"
                        + "Program æe se ukljuèiti nakon ažuriranja.\n"
                        + "Trenutna verzija: " + c + "\n"
                        + "Nova verzija: " + l + "\n");
                return true;
            }
            return false;
        } catch (StringIndexOutOfBoundsException ex) {
            return false;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public static String getThisVersion(Class theClass) throws MalformedURLException, IOException {
        String implVer = "";
        try {// Example using HTMLEmail from Apache Commons Email 
            //Class theClass = MeniWindow.class;
            // Find the path of the compiled class 
            String classPath = theClass.getResource(theClass.getSimpleName() + ".class").toString();
            // Find the path of the lib which includes the class 
            String libPath = classPath.substring(0, classPath.lastIndexOf("!"));
            // Find the path of the file inside the lib jar 
            String filePath = libPath + "!/META-INF/MANIFEST.MF";
            // We look at the manifest file, getting two attributes out of it 
            Manifest manifest = new Manifest(new URL(filePath).openStream());
            Attributes attr = manifest.getMainAttributes();
            implVer = attr.getValue("Implementation-Version");
        } catch (StringIndexOutOfBoundsException ex) {
            implVer = "";
            //throw new StringIndexOutOfBoundsException();
        } finally {
            return implVer;
        }
    }

    private static String getLatestVersion() throws MalformedURLException, IOException {
        // Find the path of the lib which includes the class 
        //Properties connectionProperties = rs.bane.server.Connecting.readPropertiesFile("connection.properties");
        //String libPath = "jar:file://" + connectionProperties.getProperty("server") + "/razmena/INSTALACIJE/Alati/Alati.jar";
        String libPath = "jar:file://banesombor/razmena/INSTALACIJE/Alati/Alati.jar";
        // Find the path of the file inside the lib jar 
        String filePath = libPath + "!/META-INF/MANIFEST.MF";
        // We look at the manifest file, getting two attributes out of it 
        Manifest manifest = new Manifest(new URL(filePath).openStream());
        Attributes attr = manifest.getMainAttributes();
        String implVer = attr.getValue("Implementation-Version");
        return implVer;
    }
}
