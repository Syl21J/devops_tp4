package datastruct;

import static org.junit.Assert.*;

import org.junit.Test;

public class MyUnsortedListTest {

    /**
     * Bugs trouvés :
     *
     * - testPopLastNull(), PopLast renvoie une IndexOutOfBoundsException et non une EmptyListException
     *
     * - testPopLastAll(), Lors des popLast(), size n'est pas décrémenté.
     *
     * - testAppendNullInt/String, On pouvait ajouter un element null dans une liste d'entier
     * - de meme pour testPrependNullInt/String
     *
     */



    //------Creation------

    @Test
    public void testCreateList() {
        UnsortedList<Integer> list = MyUnsortedList.of();
        assertNotNull("Creation Liste non nulle", list);
    }

    @Test
    public void testCreateListSize() {
        UnsortedList<Integer> list = MyUnsortedList.of();
        assertEquals("Creation Liste taille 0", 0, list.size());
    }

    @Test
    public void testCreateListString() {
        UnsortedList<String> list = MyUnsortedList.of("aaa", "bbb", "ccc");
        assertEquals("Liste avec string", 3, list.size());
    }

    @Test
    public void testCreateListInt() {
        UnsortedList<Integer> list = MyUnsortedList.of(1, 2, 3, 4);
        assertEquals("Liste avec int", 4, list.size());
    }

    //------is Empty------

    @Test
    public void testIsEmpty() {
        UnsortedList<Integer> list = MyUnsortedList.of();
        assertTrue("Liste vide",list.isEmpty());
    }

    @Test
    public void testNotEmpty() {
        UnsortedList<Integer> list = MyUnsortedList.of();
        list.append(768);
        assertFalse("Liste non vide",list.isEmpty());
    }

    @Test
    public void testNotEmptyBig() {
        UnsortedList<Integer> list = MyUnsortedList.of();
        int val = 10000;
        for(int i = 0; i < val; i++) {
            list.append(i);
        }
        assertFalse("Liste non vide",list.isEmpty());
    }

    //------Size Int------
    @Test
    public void testSizeInt0() {
        UnsortedList<Integer> list = MyUnsortedList.of();
        assertEquals("Taille int 0",0,list.size());
    }

    @Test
    public void testSizeInt1() {
        UnsortedList<Integer> list = MyUnsortedList.of();
        list.append(1);
        assertEquals("Taille int 1",1,list.size());
    }

    @Test
    public void testSizeIntBig() {
        UnsortedList<Integer> list = MyUnsortedList.of();
        int val = 10000;
        for(int i = 0; i < val; i++) {
            list.append(i);
        }
        assertEquals("Taille int grande",val,list.size());
    }

    //------Size Strings------
    @Test
    public void testSizeString0() {
        UnsortedList<String> list = MyUnsortedList.of();
        assertEquals("Taille string 0",0,list.size());
    }

    @Test
    public void testSizeString1() {
        UnsortedList<String> list = MyUnsortedList.of();
        list.append("bonjour");
        assertEquals("Taille string 1",1,list.size());
    }

    @Test
    public void testSizeStringBig() {
        UnsortedList<String> list = MyUnsortedList.of();
        int val = 10000;
        for(int i = 0; i < val; i++) {
            String s = "aaaaaaa";

            list.append(s);
        }
        assertEquals("Taille string grande",val,list.size());
    }

    //------Prepend------

    @Test(expected = ElementConflictException.class)
    public void testPrependNullInt() {
        UnsortedList<Integer> list = MyUnsortedList.of();
        list.prepend(null);
    }

    @Test(expected = ElementConflictException.class)
    public void testPrependNullString() {
        UnsortedList<String> list = MyUnsortedList.of();
        list.prepend(null);
    }

    @Test
    public void testPrependInt1() {
        UnsortedList<Integer> list = MyUnsortedList.of();
        list.prepend(378);
        assertEquals("Prepend un element",(int)378, (int)list.pop());
    }

    @Test
    public void testPrependInt2() {
        UnsortedList<Integer> list = MyUnsortedList.of();
        list.prepend(378);
        list.prepend(973);
        list.prepend(222);
        list.pop();
        assertEquals("Dernier prepend", (int) 973, (int) list.pop());
    }

    @Test
    public void testPrependIntBig() {
        UnsortedList<Integer> list = MyUnsortedList.of();
        int val = 10000;
        for(int i = 0; i < val+1; i++) {
            list.prepend(i);
        }
        assertEquals("Prepend beaucoup d'elements", val, (int) list.pop());
    }

    @Test
    public void testPrependString1() {
        UnsortedList<String> list = MyUnsortedList.of();
        list.prepend("aaaaaa");
        assertEquals("Prepend un element","aaaaaa", list.pop());
    }

    @Test
    public void testPrependString2() {
        UnsortedList<String> list = MyUnsortedList.of();
        list.prepend("aaaaaa");
        list.prepend("bbbbbb");
        list.pop();
        assertEquals("Dernier prepend", "aaaaaa", list.pop());
    }

    @Test
    public void testPrependStringBig() {
        UnsortedList<String> list = MyUnsortedList.of();
        int val = 10000;
        list.prepend("First");
        for(int i = 0; i < val; i++) {
            char c = (char) (48 + i%74);
            String s = ("" + c).repeat(6);
            list.prepend(s);
        }
        list.prepend("Last");
        assertEquals("Prepend beaucoup d'elements", "Last", list.pop());
    }

    //------Append------
    @Test(expected = ElementConflictException.class)
    public void testAppendNullInt() {
        UnsortedList<Integer> list = MyUnsortedList.of();
        list.append(null);
    }

    @Test(expected = ElementConflictException.class)
    public void testAppendNullString() {
        UnsortedList<String> list = MyUnsortedList.of();
        list.append(null);
    }

    @Test
    public void testAppend1() {
        UnsortedList<Integer> list = MyUnsortedList.of();
        list.append(4);
        assertEquals("Append un element",(int)4, (int)list.pop());
    }

    @Test
    public void testAppend2() {
        UnsortedList<Integer> list = MyUnsortedList.of();
        list.append(378);
        list.append(222);
        assertEquals("Dernier Append", (int) 222, (int) list.popLast());
    }

    @Test
    public void testAppendBig() {
        UnsortedList<Integer> list = MyUnsortedList.of();
        int val = 10000;
        for(int i = 0; i < val+1; i++) {
            list.append(i);
        }
        assertEquals("Append beaucoup d'elements", val, (int) list.popLast());
    }

    @Test
    public void testAppendString1() {
        UnsortedList<String> list = MyUnsortedList.of();
        list.append("aaaaa");
        assertEquals("Append un element","aaaaa", list.pop());
    }//oui

    @Test
    public void testAppendString2() {
        UnsortedList<String> list = MyUnsortedList.of();
        list.append("aaaaa");
        list.append("bbbbb");
        assertEquals("Dernier Append", "bbbbb", list.popLast());
    }

    @Test
    public void testAppendStringBig() {
        UnsortedList<String> list = MyUnsortedList.of();
        int val = 10000;
        list.append("First");
        for(int i = 0; i < val; i++) {
            char c = (char) (48 + i%74);
            String s = ("" + c).repeat(6);
            list.append(s);
        }
        list.append("Last");
        assertEquals("Append beaucoup d'elements", "Last", list.popLast());
    }
    //Test pas ouf ouf

    //------Insert------

    @Test
    public void testInsertIntStart() {
        UnsortedList<Integer> list = MyUnsortedList.of();
        list.append(1111);
        list.append(2222);
        list.append(3333);
        list.append(4444);

        list.insert(9999, 0);

        assertEquals("Insert int 0", 9999, (int) list.pop());
    }

    @Test
    public void testInsertIntEnd() {
        UnsortedList<Integer> list = MyUnsortedList.of();
        list.append(1111);
        list.append(2222);
        list.append(3333);
        list.append(4444);

        list.insert(9999, list.size());

        assertEquals("Insert int last", 9999, (int) list.popLast());
    }

    @Test
    public void testInsertIntMid() {
        UnsortedList<Integer> list = MyUnsortedList.of();
        list.append(1111);
        list.append(2222);
        list.append(3333);
        list.append(4444);

        list.insert(9999, 2);
        list.popLast();
        list.popLast();

        assertEquals("Insert int middle", 9999, (int) list.popLast());
    }

    @Test
    public void testInsertStringStart() {
        UnsortedList<String> list = MyUnsortedList.of();
        list.append("aaaaa");
        list.append("bbbbb");
        list.append("ccccc");
        list.append("ddddd");

        list.insert("bonjour", 0);

        assertEquals("Insert 0", "bonjour", list.pop());
    }

    @Test
    public void testInsertStringEnd() {
        UnsortedList<String> list = MyUnsortedList.of();
        list.append("aaaaa");
        list.append("bbbbb");
        list.append("ccccc");
        list.append("ddddd");

        list.insert("bonjour", list.size());

        assertEquals("Insert last", "bonjour", list.popLast());
    }

    @Test( expected = IndexOutOfBoundsException.class)
    public void testInsertStringOOBMin() throws Exception{
        UnsortedList<String> list = MyUnsortedList.of();
        list.append("aaaaa");
        list.append("bbbbb");
        list.append("ccccc");
        list.append("ddddd");

        list.insert("bonjour", -1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testInsertStringOOBMax() throws Exception{
        UnsortedList<String> list = MyUnsortedList.of();
        list.append("aaaaa");
        list.append("bbbbb");
        list.append("ccccc");
        list.append("ddddd");

        list.insert("bonjour", list.size()+1);
    }

    //------Pop------

    @Test
    public void testPopString() {
        UnsortedList<String> list = MyUnsortedList.of();
        list.append("aaaaa");
        list.append("bbbbb");
        list.append("ccccc");
        list.append("ddddd");

        assertEquals("Pop string", "aaaaa", list.pop());
    }

    @Test
    public void testPopInt() {
        UnsortedList<Integer> list = MyUnsortedList.of();
        list.append(1111);
        list.append(2222);
        list.append(3333);
        list.append(4444);

        assertEquals("Pop int", 1111, (int) list.pop());
    }

    @Test(expected = datastruct.EmptyListException.class)
    public void testPopNull() throws Exception{
        UnsortedList<Integer> list = MyUnsortedList.of();
        list.pop();
    }

    @Test
    public void testPopAll(){
        UnsortedList<Integer> list = MyUnsortedList.of();
        int val = 10000;
        for(int i = 0; i < val+1; i++) {
            list.append(i);
        }

        for(int i = 0; i < val+1; i++) {
            list.pop();
        }

        assertEquals("Pop all", 0, list.size());
    }

    //------PopLast------

    @Test(expected = datastruct.EmptyListException.class)
    public void testPopLastNull() throws Exception{
        UnsortedList<Integer> list = MyUnsortedList.of();
        list.popLast();
    }

    @Test
    public void testPopLastSimple() {
        UnsortedList<Integer> list = MyUnsortedList.of();
        list.append(6);
        assertEquals("Pop last 1 element", 6, (int) list.popLast());
    }

    @Test
    public void testPopLastBig() {
        UnsortedList<Integer> list = MyUnsortedList.of();
        int val = 10000;
        for(int i = 0; i < val+1; i++) {
            list.append(i);
        }

        assertEquals("Pop last bcp d'elements", val, (int) list.popLast());
    }

    @Test
    public void testPopLastAll(){
        UnsortedList<Integer> list = MyUnsortedList.of();
        int val = 10000;
        for(int i = 0; i < val+1; i++) {
            list.append(i);
        }

        for(int i = 0; i < val+1; i++) {
            list.popLast();
        }

        assertEquals("Pop last all", 0, list.size());
    }

    //------Remove------

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveNull() throws Exception{
        UnsortedList<Integer> list = MyUnsortedList.of();
        list.remove(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveOOB() throws Exception{
        UnsortedList<Integer> list = MyUnsortedList.of();
        int val = 10000;
        for(int i = 0; i < val+1; i++) {
            list.append(i);
        }
        list.remove(list.size()+1);
    }

    @Test
    public void testRemoveSimple(){
        UnsortedList<Integer> list = MyUnsortedList.of();
        list.append(1111);
        assertEquals("Remove simple", 1111, (int) list.remove(0));
    }

    @Test
    public void testRemoveBigFirst(){
        UnsortedList<Integer> list = MyUnsortedList.of();
        int val = 10000;
        for(int i = 0; i < val+1; i++) {
            list.append(i);
        }

        assertEquals("Remove big first", 0, (int) list.remove(0));
    }

    @Test
    public void testRemoveBigLast(){
        UnsortedList<Integer> list = MyUnsortedList.of();
        int val = 10000;
        for(int i = 0; i < val+1; i++) {
            list.append(i);
        }

        assertEquals("Remove big last", val, (int) list.remove(list.size()-1));
    }

    @Test
    public void testRemoveBigMiddle(){
        UnsortedList<Integer> list = MyUnsortedList.of();
        int val = 10000;
        for(int i = 0; i < val+1; i++) {
            list.append(i);
        }

        assertEquals("Remove Middle", val/2, (int) list.remove(val/2));
    }

    @Test
    public void testRemoveAll(){
        UnsortedList<Integer> list = MyUnsortedList.of();
        int val = 10000;
        for(int i = 0; i < val+1; i++) {
            list.append(i);
        }

        for(int i = val; i >= 0; i--) {
            list.remove(i);
        }

        assertEquals("Remove all", 0, list.size());
    }

}