import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArtExhibition {

    public static void main(String[] args) {
        Gallery gallery = new ConcreteGallery();
        gallery.menu();
    }

    // Abstract class for Artwork
    static abstract class Artwork {
        private String title;
        private String artist;
        private int year;

        // Constructor
        public Artwork(String title, String artist, int year) {
            this.title = title;
            this.artist = artist;
            this.year = year;
        }

        public String getTitle() {
            return title;
        }

        public String getArtist() {
            return artist;
        }

        public int getYear() {
            return year;
        }

        // Abstract method for polymorphism
        public abstract String getDetails();
    }

    // Subclass for Paintings
    static class Painting extends Artwork {
        private String medium;

        public Painting(String title, String artist, int year, String medium) {
            super(title, artist, year);
            this.medium = medium;
        }

        public String getMedium() {
            return medium;
        }

        @Override
        public String getDetails() {
            return "Title: " + getTitle() + ", Artist: " + getArtist() + ", Year: " + getYear() + ", Medium: " + medium;
        }
    }

    // Subclass for Sculptures
    static class Sculpture extends Artwork {
        private String material;

        public Sculpture(String title, String artist, int year, String material) {
            super(title, artist, year);
            this.material = material;
        }

        public String getMaterial() {
            return material;
        }

        @Override
        public String getDetails() {
            return "Title: " + getTitle() + ", Artist: " + getArtist() + ", Year: " + getYear() + ", Material: " + material;
        }
    }

    // Interface for Gallery
    interface Gallery {
        void addArtwork(String title, String artist, int year, String type, String extraDetail);

        void listArtworks();

        void deleteArtwork(String title);

        void menu();

        void close();
    }

    // Concrete implementation of Gallery
    static class ConcreteGallery implements Gallery {
        private List<Artwork> artworks;
        private static int totalArtworks = 0;

        public ConcreteGallery() {
            this.artworks = new ArrayList<>();
            System.out.println("Gallery initialized.");
        }

        @Override
        public void addArtwork(String title, String artist, int year, String type, String extraDetail) {
            Artwork artwork = null;
            switch (type.toLowerCase()) {
                case "painting":
                    artwork = new Painting(title, artist, year, extraDetail);
                    break;
                case "sculpture":
                    artwork = new Sculpture(title, artist, year, extraDetail);
                    break;
                default:
                    System.out.println("Invalid artwork type.");
                    return;
            }
            artworks.add(artwork);
            totalArtworks++;
            System.out.println("'" + title + "' has been added.");
        }

        @Override
        public void listArtworks() {
            if (artworks.isEmpty()) {
                System.out.println("The gallery is empty.");
            } else {
                System.out.println("Gallery Artworks:");
                int index = 1;
                for (Artwork artwork : artworks) {
                    System.out.println(index++ + ". " + artwork.getDetails());
                }
            }
        }

        @Override
        public void deleteArtwork(String title) {
            boolean found = false;
            for (Artwork artwork : artworks) {
                if (artwork.getTitle().equalsIgnoreCase(title)) {
                    artworks.remove(artwork);
                    totalArtworks--;
                    System.out.println("'" + title + "' has been deleted.");
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("Artwork titled '" + title + "' not found.");
            }
        }

        @Override
        public void menu() {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("\nGallery Menu:");
                System.out.println("1. Add Artwork");
                System.out.println("2. List Artworks");
                System.out.println("3. Delete Artwork");
                System.out.println("4. Display Total Artworks");
                System.out.println("5. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.print("Enter artwork title: ");
                        String title = scanner.nextLine();
                        System.out.print("Enter artist name: ");
                        String artist = scanner.nextLine();
                        System.out.print("Enter year of creation: ");
                        int year = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter type of artwork (Painting/Sculpture): ");
                        String type = scanner.nextLine();
                        System.out.print("Enter medium/material: ");
                        String extraDetail = scanner.nextLine();
                        addArtwork(title, artist, year, type, extraDetail);
                        break;
                    case 2:
                        listArtworks();
                        break;
                    case 3:
                        System.out.print("Enter title of artwork to delete: ");
                        String deleteTitle = scanner.nextLine();
                        deleteArtwork(deleteTitle);
                        break;
                    case 4:
                        System.out.println("Total artworks: " + totalArtworks);
                        break;
                    case 5:
                        close();
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }

        @Override
        public void close() {
            artworks.clear();
            System.out.println("Gallery resources cleaned up. Thank you for using the gallery!");
        }
    }
}
