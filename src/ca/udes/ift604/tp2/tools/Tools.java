package ca.udes.ift604.tp2.tools;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Tools
{
    public static byte[] serealizer(Serializable serializable) throws IOException
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(baos);
        ObjectOutputStream oos = new ObjectOutputStream(bos);

        oos.writeObject(serializable);

        oos.close();
        bos.close();
        baos.close();

        return baos.toByteArray();
    }

    public static Serializable deserealizer(byte[] tabByte) throws IOException, ClassNotFoundException
    {
        ByteArrayInputStream bais = new ByteArrayInputStream(tabByte);
        BufferedInputStream bis = new BufferedInputStream(bais);
        ObjectInputStream ois = new ObjectInputStream(bis);

        Serializable serializable = (Serializable) ois.readObject();
        
        ois.close();
        bis.close();
        bais.close();
        return serializable;
    }
}
