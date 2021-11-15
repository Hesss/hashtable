 public class hashtable<K, V> {
        private int num; // Кол-во пар ключ-значение
        private int size;
        private K[] keys;
        private V[] values;

        public hashtable(int size) {
            keys = (K[]) new Object[size];
            values = (V[]) new Object[size];
            this.size = size;
        }

        private int hash(K key) {
            return (key.hashCode() & 0x7fffffff) % size;
        }

        public void print(){
            for(int i = 0; i<size;i++){
                if(keys[i]!=null){
                    System.out.println(values[i]);
                }
            }
        }

        public V find(K key)
        {
            int i = hash(key);
            while(values[i] != null)
            {
                if(keys[i].equals(key))
                    return values[i];
                i++;
                i = i % size;
            }
            return null;
        }

        public void put(K key, V value) {
            if (num / size > 0.7) {
                resize(size*2);
            }
            int i = hash(key);
            while (keys[i] != null && !key.equals(keys[i])) {
                i = (i + 1) % size;
            }
            if (keys[i] == null) {
                keys[i] = key;
                values[i] = value;
                return;
            }
            values[i] = value;
            num++;
        }

        public void remove(K key){
            int i = hash(key);
            while(values[i] != null)
            {
                if(keys[i].equals(key)) {
                    keys[i] = null;
                    values[i] = null;
                }
                i++;
                i = i % size;
            }

        }

        private void resize(int newCapacity) {
            hashtable<K, V> a = new hashtable<>(newCapacity);
            for (int i = 0; i < size; i++) {
                if (keys[i] != null) {
                    a.put(keys[i], values[i]);
                }
            }
            keys  = a.keys;
            values = a.values;
            size = a.size;
        }

        public static void main(String[] args){
            hashtable a = new hashtable(10);
            a.put(22,"one");
            a.put(22,"two");
            a.put(33,"tree");
            a.put(44,"four");
            a.put(55,"five");
            a.put(6,"six");
            a.print();
            System.out.println("-------------");
            a.remove(6);
            a.print();
            System.out.println("Найдено - " + a.find(44));
        }
    }