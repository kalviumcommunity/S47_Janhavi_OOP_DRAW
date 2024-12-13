public class ArtExhibition {

    public static void main(String[] args) {
        Gallery gallery = new Gallery(5); // Create a gallery with a capacity of 5 artworks
        gallery.menu(); // Display the gallery menu
    }

    // Static inner class Artwork implementing encapsulation
    static class Artwork {
        private String title;
        private String artist;
        private int year;

        // Constructor
        public Artwork(String title, String artist, int year) {
            this.title = title;
            this.artist = artist;
            this.year = year;
        }

        // Accessor (Getter) methods
        public String getTitle() {
            return title;
        }

        public String getArtist() {
            return artist;
        }

        public int getYear() {
            return year;
        }

        // Mutator (Setter) methods
        public void setTitle(String title) {
            this.title = title;
        }

        public void setArtist(String artist) {
            this.artist = artist;
        }

        public void setYear(int year) {
            this.year = year;
        }

        // Method to get artwork details
        public String getDetails() {
            return "Title: " + title + ", Artist: " + artist + ", Year: " + year;
        }
    }

    // Static inner class Gallery implementing encapsulation
    static class Gallery {
        private Artwork[] artworks;
        private int count;
        private static int totalArtworks = 0; // Static variable to track total artworks
        private static final int galleryLimit = 100; // Static constant for gallery limit

        // Constructor
        public Gallery(int capacity) {
            if (capacity > galleryLimit) {
                System.out.println("Cannot create a gallery with more than " + galleryLimit + " artworks.");
                capacity = galleryLimit;
            }
            this.artworks = new Artwork[capacity];
            this.count = 0;
        }

        // Accessor (Getter) for count
        public int getCount() {
            return count;
        }

        // Accessor (Getter) for total artworks
        public static int getTotalArtworks() {
            return totalArtworks;
        }

        // Add artwork to gallery
        public void addArtwork(String title, String artist, int year) {
            if (totalArtworks >= galleryLimit) {
                System.out.println("Cannot add more artworks. Maximum gallery limit reached.");
                return;
            }
            if (count < artworks.length) {
                artworks[count] = new Artwork(title, artist, year);
                count++;
                totalArtworks++;
                System.out.println(title + " has been added to the gallery.");
            } else {
                System.out.println("The gallery is full. Cannot add more artworks.");
            }
        }

        // List all artworks
        public void listArtworks() {
            if (count == 0) {
                System.out.println("The gallery is empty.");
            } else {
                System.out.println("Artworks in the gallery:");
                for (int i = 0; i < count; i++) {
                    System.out.println((i + 1) + ". " + artworks[i].getDetails());
                }
            }
        }

        // Delete an artwork by title
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
                    System.out.println(title + " has been deleted from the gallery.");
                    break;
                }
            }
            if (!found) {
                System.out.println("Artwork titled '" + title + "' not found in the gallery.");
            }
        }

        // Display total artworks using encapsulation
        public static void displayTotalArtworks() {
            System.out.println("Static Method: Total Artworks across all galleries: " + totalArtworks);
        }

        // Menu for gallery interaction
        public void menu() {
            java.util.Scanner scanner = new java.util.Scanner(System.in);
            while (true) {
                System.out.println("\nGallery Menu:");
                System.out.println("1. Add Artwork");
                System.out.println("2. List Artworks");
                System.out.println("3. Delete Artwork");
                System.out.println("4. Display Total Artworks");
                System.out.println("5. Display Total Artworks (Static)");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.print("Enter the title of the artwork: ");
                        String title = scanner.nextLine();
                        System.out.print("Enter the artist of the artwork: ");
                        String artist = scanner.nextLine();
                        System.out.print("Enter the year of creation: ");
                        int year = scanner.nextInt();
                        scanner.nextLine();
                        addArtwork(title, artist, year);
                        break;
                    case 2:
                        listArtworks();
                        break;
                    case 3:
                        System.out.print("Enter the title of the artwork to delete: ");
                        String deleteTitle = scanner.nextLine();
                        deleteArtwork(deleteTitle);
                        break;
                    case 4:
                        System.out.println("Total Artworks across all galleries: " + getTotalArtworks());
                        break;
                    case 5:
                        displayTotalArtworks(); // Static method call
                        break;
                    case 6:
                        System.out.println("Exiting the gallery. Goodbye!");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }
    }
}
