
import java.util.Scanner;

public class ArtExhibition {

    
    public static class Artwork {
        
        private String title;
        private String artist;
        private int year;

        
        public Artwork(String title, String artist, int year) {
            this.title = title;
            this.artist = artist;
            this.year = year;
        }

        
        public String getDetails() {
            return "Title: " + this.title + ", Artist: " + this.artist + ", Year: " + this.year;
        }

        
        public String getTitle() {
            return this.title;
        }
    }

    
    public static class Gallery {
       
        private Artwork[] artworks;
        private int count;

       
        public Gallery(int capacity) {
            artworks = new Artwork[capacity];
            count = 0; 
        }

        
        public void addArtwork(Artwork artwork) {
            if (count < artworks.length) {
                artworks[count] = artwork; 
                count++;
                System.out.println(artwork.getTitle() + " has been added to the gallery.");
            } else {
                System.out.println("The gallery is full. Cannot add more artworks.");
            }
        }

        
        public void listArtworks() {
            if (count == 0) {
                System.out.println("The gallery is empty.");
            } else {
                System.out.println("Artworks in the gallery:");
                for (int i = 0; i < count; i++) {
                    System.out.println(artworks[i].getDetails()); // Accessing array elements
                }
            }
        }
    }

   
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        
        Gallery gallery = new Gallery(5);

       
        boolean addMoreArtworks = true;
        while (addMoreArtworks) {
            System.out.println("Enter the title of the artwork:");
            String title = scanner.nextLine();

            System.out.println("Enter the artist of the artwork:");
            String artist = scanner.nextLine();

            System.out.println("Enter the year of creation:");
            int year = scanner.nextInt();
            scanner.nextLine();  

            
            Artwork artwork = new Artwork(title, artist, year);
            gallery.addArtwork(artwork);

            System.out.println("Do you want to add another artwork? (yes/no)");
            String response = scanner.nextLine();
            if (!response.equalsIgnoreCase("yes")) {
                addMoreArtworks = false;
            }
        }

        
        gallery.listArtworks();
        scanner.close();
    }
}
