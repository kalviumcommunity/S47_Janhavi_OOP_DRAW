public class ArtExhibition {

    public static void main(String[] args) {
        Gallery gallery = new ConcreteGallery(5);
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

        public abstract String getDetails();
    }

    // Concrete implementation of Artwork
    static class ConcreteArtwork extends Artwork {

        public ConcreteArtwork(String title, String artist, int year) {
            super(title, artist, year);
        }

        @Override
        public String getDetails() {
            return "Title: " + getTitle() + ", Artist: " + getArtist() + ", Year: " + getYear();
        }
    }

    // Interface for Gallery
    interface Gallery {
        void addArtwork(String title, String artist, int year);

        void listArtworks();

        void deleteArtwork(String title);

        void menu();

        void close(); // Simulating destructor for cleanup

        static void displayTotalArtworks() {
            System.out.println("Total Artworks in all galleries.");
        }
    }

    // Concrete implementation of Gallery
    static class ConcreteGallery implements Gallery {
        private Artwork[] artworks;
        private int count;
        private static int totalArtworks = 0;
        private static final int galleryLimit = 100;

        // Constructor
        public ConcreteGallery(int capacity) {
            if (capacity > galleryLimit) {
                System.out.println("Gallery capacity is limited to " + galleryLimit + ".");
                capacity = galleryLimit;
            }
            this.artworks = new Artwork[capacity];
            this.count = 0;
            System.out.println("Gallery initialized with capacity for " + capacity + " artworks.");
        }

        // Simulated destructor
        @Override
        public void close() {
            this.artworks = null; // Freeing up memory
            System.out.println("Gallery resources cleaned up. Thank you for using the gallery!");
        }

        @Override
        public void addArtwork(String title, String artist, int year) {
            if (totalArtworks >= galleryLimit) {
                System.out.println("Gallery is full. Cannot add more artworks.");
                return;
            }
            if (count < artworks.length) {
                artworks[count] = new ConcreteArtwork(title, artist, year);
                count++;
                totalArtworks++;
                System.out.println("'" + title + "' has been added.");
            } else {
                System.out.println("The gallery is at capacity. Cannot add more artworks.");
            }
        }

        @Override
        public void listArtworks() {
            if (count == 0) {
                System.out.println("The gallery is empty.");
            } else {
                System.out.println("Gallery Artworks:");
                for (int i = 0; i < count; i++) {
                    System.out.println((i + 1) + ". " + artworks[i].getDetails());
                }
            }
        }

        @Override
        public void deleteArtwork(String title) {
            boolean found = false;
            for (int i = 0; i < count; i++) {
                if (artworks[i].getTitle().equalsIgnoreCase(title)) {
                    found = true;
                    for (int j = i; j < count - 1; j++) {
                        artworks[j] = artworks[j + 1];
                    }
                    artworks[count - 1] = null;
                    count--;
                    totalArtworks--;
                    System.out.println("'" + title + "' has been deleted.");
                    break;
                }
            }
            if (!found) {
                System.out.println("Artwork titled '" + title + "' not found.");
            }
        }

        @Override
        public void menu() {
            java.util.Scanner scanner = new java.util.Scanner(System.in);
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
                        addArtwork(title, artist, year);
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
    }
}
