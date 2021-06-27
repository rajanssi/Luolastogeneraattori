package cavegame.utils;

/**
 * Taulukkolistatoteutus, joka kasvaa ja kutistuu dynaamisesti tarvittavan tilan perusteella. Lisäysten tasoitettu
 * aikavaativuus O(1), poistojen O(n).
 * @param <T> Taulukkolistaan voi lisätä minkä tahansa tyyppisiä olioita.
 */
public class ArrayList<T> {
    private T[] data;
    private int size;

    public ArrayList() {
        data = (T[]) new Object[10];

        for (int i = 0; i < 10; i++) {
            data[i] = null;
        }

        size = 0;
    }

    /**
     * Palauttaa taulukkolistan alkioiden määrän.
     * @return Taulukkolistan alkioiden määrä.
     */
    public int size() {
        return this.size;
    }

    /**
     * Palauttaa taulukkolistan alkion annetussa indeksissä.
     * @param index Indeksi, josta taulukkolistan alkio haetaan.
     * @return Taulukkolistan alkio annetusta indeksistä
     */
    public T get(int index) {
        return data[index];
    }

    /**
     * Asettaa annettuun indeksiin parametrina annetun alkion.
     * @param index Indeksi, jonka sisältöä muokataan.
     * @param item Alkio, joka sijoitetaan annettuun indeksiin.
     */
    public void set(int index, T item) {
        if (index >= size) {
            return;
        }
        data[index] = item;
    }

    /**
     * Lisää alkion taulukkolistaan.
     * @param item Lisättävä alkio.
     */
    public void add(T item) {
        if (size == data.length) {
            grow();
        }
        data[size] = item;
        size++;
    }

    /**
     * Poistaa alkion taulukkolistasta.
     * @param value Poistettava alkio.
     */
    public void remove(T value) {
        int index = indexOf(value);
        if (index < 0) {
            return;
        }

        shrink(index);
        size--;
    }

    /**
     * Palauttaa haetun alkion indeksin sisäisestä taulukosta.
     * @param value Heattava alkio.
     * @return Alkion indeksi jos se löytyy, muuten -1.
     */
    private int indexOf(T value) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(value)) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Siirretään sisäisen taulukon alkioita vasemmalla annetusta indeksistä.
     * @param index Indeksi, josta lähtien siirretään taulukon alkioita vasemmalle.
     */
    private void shrink(int index) {
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
    }

    /**
     * Taulukkolistan sisäisen taulukon täyttyessä kaksinkertaistetaan sen koko.
     */
    private void grow() {
        var temp = (T[]) new Object[data.length * 2];
        for (int i = 0; i < data.length; i++) {
            temp[i] = data[i];
        }
        data = temp;
    }
}
