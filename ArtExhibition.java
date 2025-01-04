import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArtExhibition {

    public static void main(String[] args) {
        ManageableGallery gallery = new ConcreteGallery();
        gallery.menu();
    }

    static abstract class Artwork {
        
        private String title;
        private String artist;
        private int year;

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

        public abstract String getDetails();

        public abstract String exhibitStyle();

        public void display() {
            System.out.println("This is a general artwork.");
        }
    }

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

        @Override
        public String exhibitStyle() {
            return "Displayed in a frame on the wall with spotlight.";
        }

        @Override
        public void display() {
            System.out.println("This is a painting titled: " + getTitle());
        }
    }

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

        @Override
        public String exhibitStyle() {
            return "Displayed on a pedestal with surrounding lighting.";
        }

        @Override
        public void display() {
            System.out.println("This is a sculpture titled: " + getTitle());
        }
    }


    interface AddableGallery {
        void addArtwork(String title, String artist, int year, String type, String extraDetail);
    }

    interface ListableGallery {
        void listArtworks();
    }

    interface DeletableGallery {
        void deleteArtwork(String title);
    }

    interface ManageableGallery extends AddableGallery, ListableGallery, DeletableGallery {
        void menu();
        void close();
    }

   
    static class ConcreteGallery implements ManageableGallery {
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
                for (Artwork artwork : artworks) {
                    artwork.display();
                    System.out.println(artwork.getDetails());
                    System.out.println("   Exhibit Style: " + artwork.exhibitStyle());
                }
            }
        }

        @Override
        public void deleteArtwork(String title) {
            boolean found = false;
            for (int i = 0; i < artworks.size(); i++) {
                if (artworks.get(i).getTitle().equalsIgnoreCase(title)) {
                    artworks.remove(i);
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
                        scanner.nextLine(); // Consume leftover newline
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
