package string;

import java.util.HashSet;
import java.util.Set;

import static java.lang.System.out;
import static java.lang.System.setOut;

public class StringTest {
    public static void main(String[] args) {
        /*if(isPalindromeByArray("qwwq")){
            out.println("yes,it is a palindrome String");
        }else{
            out.println("sorry,it isn't a ps");
        }*/
        //isPalindromeByLink("luoyuchao");
        Node node = convertStrToLink("luoyuchao");
        out.println("原始链表是：");
        printLink(node);
        out.println();
        out.println("反转后链表为：");
        Node newHead = backLink(node);
        printLink(newHead);

    }

    /**
     * 数组方式实现
     *
     * @param str
     * @return
     */
    public static boolean isPalindromeByArray(String str) {
        char[] chars = str.toCharArray();
        for (int i = 0, j = str.length() - 1; i < j; i++, j--) {
            if (chars[i] != chars[j]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 单向链表方式实现
     *
     * @param str
     * @return
     */
    public static boolean isPalindromeByLink(String str) {

        //将字符串转为链表，并返回头节点
        Node head = new Node(str.charAt(0));
        Node nodePre = head;
        for (int i = 1; i < str.length(); i++) {
            Node nodeNext = new Node(str.charAt(i));
            nodePre.next = nodeNext;
            nodePre = nodePre.next;
        }
        /*while(head != null){
            out.print(head.getValue() +",");
            head = head.next;
        }*/

        return true;
    }

    /**
     * 字符串转换为链表
     *
     * @param str
     * @return
     */
    public static Node convertStrToLink(String str) {
        if (str == null) {
            return null;
        }
        Node head = new Node(str.charAt(0));
        Node node = head;
        for (int i = 1; i < str.length(); i++) {
            Node temp = new Node(str.charAt(i));
            node.next = temp;
            node = node.next;
        }
        return head;
    }

    /**
     * 打印链表
     *
     * @param node
     */
    public static void printLink(Node node) {
        while (node != null) {
            out.print(node.getValue() + ",");
            node = node.next;
        }
    }

    /**
     * 反转链表
     *
     * @param head
     * @return
     */
    public static Node backLink(Node head) {
        Node newHead = null;
        Node node = null;
        while (head != null) {
            node = head;
            head = head.next;

            node.next = newHead;
            newHead = node;
        }
        return newHead;
    }

    /**
     * 链表中环检测（快慢指针法）
     *
     * @param head
     * @return
     */
    public static boolean isRingLink1(Node head) {
        if (head == null) {
            return false;
        }
        Node nodeSlow = head;
        Node nodeFast = head.next;
        while (nodeFast != null && nodeFast.next != null) {
            nodeSlow = nodeSlow.next;
            nodeFast = nodeFast.next.next;
            if (nodeFast == nodeSlow) {
                return true;
            }

        }
        return false;
    }

    /**
     * 链表中环检测（足迹法）
     *
     * @param head
     * @return
     */
    public static boolean isRingLink2(Node head) {
        Set<Node> set = new HashSet<>();
        while (head != null) {
            if (set.contains(head)) {
                return true;
            } else {
                set.add(head);
                head = head.next;
            }
        }
        return false;
    }

}
