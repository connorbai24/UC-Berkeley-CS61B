import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {

     @Test
     @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
     void noNonTrivialFields() {
         List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
                 .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
                 .toList();

         assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
     }

     @Test
     public void addFirstTest() {
         Deque61B<Integer> list = new ArrayDeque61B<>();

         for (int i = 0; i < 11; i++) {
             list.addFirst(i);
         }
         assertThat(list.toList()).containsExactly(10,9,8,7,6,5,4,3,2,1,0).inOrder();
     }

     @Test
     public void addLastTest() {
         Deque61B<Integer> list = new ArrayDeque61B<>();

         for (int i = 0; i < 11; i++) {
             list.addLast(i);
         }
         assertThat(list.toList()).containsExactly(0,1,2,3,4,5,6,7,8,9,10).inOrder();
     }

     @Test
     public void addFirstLastTest() {
         Deque61B<Integer> list = new ArrayDeque61B<>();

         list.addLast(0);
         list.addLast(1);
         list.addFirst(9);
         list.addFirst(8);
         assertThat(list.toList()).containsExactly(8,9,0,1).inOrder();
     }

     @Test
     public void getTest() {
         Deque61B<Integer> list = new ArrayDeque61B<>();

         list.addFirst(3);
         list.addFirst(4);
         list.addLast(9);
         assertThat(list.get(-1)).isEqualTo(null); // edge case for testing
         assertThat(list.get(0)).isEqualTo(4);
         assertThat(list.get(1)).isEqualTo(3);
         assertThat(list.get(2)).isEqualTo(9);
         assertThat(list.get(3)).isEqualTo(null);
     }

     @Test
     public void isEmpty_and_sizeTest() {
         Deque61B<Integer> list = new ArrayDeque61B<>();

         assertThat(list.isEmpty()).isTrue();
         assertThat(list.size()).isEqualTo(0);

         list.addFirst(3);
         assertThat(list.isEmpty()).isFalse();
         assertThat(list.size()).isEqualTo(1);

         list.addFirst(4);
         list.addLast(9);
         assertThat(list.size()).isEqualTo(3);
     }

     @Test
     public void removeFirstTest() {
         Deque61B<Integer> list = new ArrayDeque61B<>();

         for (int i = 0; i < 11; i++) {
             list.addLast(i);
         }
         assertThat(list.toList()).containsExactly(0,1,2,3,4,5,6,7,8,9,10).inOrder();

         for (int i = 0; i < 10; i++) {
             assertThat(list.removeFirst()).isEqualTo(i);
         }
         assertThat(list.toList()).containsExactly(10).inOrder();
     }

     @Test
     public void removeLastTest() {
        Deque61B<Integer> list = new ArrayDeque61B<>();

        for (int i = 0; i < 16; i++) {
            list.addFirst(i);
        }

        for (int i = 0; i < 16; i++) {
            assertThat(list.removeLast()).isEqualTo(i);
        }
    }
}
