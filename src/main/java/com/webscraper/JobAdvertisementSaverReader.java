package com.webscraper;

import com.webscraper.model.JobAdvertisement;

import java.io.*;

public class JobAdvertisementSaverReader {
    /**
     * Serialize and save a job advertisement on local disk
     * @param jobAdvertisement the job advertisement that should be saved
     */
    public void saveAsFile(JobAdvertisement jobAdvertisement) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("./jobAdvertisement.ser");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(jobAdvertisement);

            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Load a saved job advertisement from disk
     * @return the loaded job advertisement
     */
    public JobAdvertisement LoadFromFile() {
        try {
            FileInputStream fileInputStream = new FileInputStream("./jobAdvertisement.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            JobAdvertisement jobAdvertisement = (JobAdvertisement) objectInputStream
                    .readObject();

            objectInputStream.close();
            fileInputStream.close();

            return jobAdvertisement;
        } catch (FileNotFoundException e) {
            JobAdvertisement placeholder = new JobAdvertisement();
            placeholder.setTitle("placeholder");
            return placeholder;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
