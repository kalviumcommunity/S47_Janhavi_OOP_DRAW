
public class ArtExhibition {

    public static class Artwork {
        private String title;
        private String artist;
        private int year;



        public String getDetails() {
            return "Title: " + title + ", Artist: " + artist + ", Year: " + year;
        }

        public String getTitle() {
            return title;
        }
    }

    public static class Gallery {
    
        public void listArtworks() {
            System.out.println("Listing artworks is not yet implemented.");
        }
    }

    public static void main(String[] args) {

        Gallery gallery = new Gallery();
        gallery.listArtworks();
        }
}
