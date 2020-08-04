package tree;

import java.util.*;

public class TreeUtils {
    public static void main(String[] args) {
        TreeNode nodeA = new TreeNode("A");
        TreeNode nodeB = new TreeNode("B");
        TreeNode nodeC = new TreeNode("C");
        TreeNode nodeD = new TreeNode("D");
        TreeNode nodeE = new TreeNode("E");
        TreeNode nodeF = new TreeNode("F");
        TreeNode nodeG = new TreeNode("G");

        nodeA.left = nodeB;
        nodeA.right = nodeC;
        nodeB.left = nodeD;
        nodeB.right = nodeE;
        nodeC.left = nodeF;
        nodeC.right = nodeG;
        System.out.println("递归者为神>>>");
        prePrintRec(nodeA);
        System.out.println();
        midPrintRec(nodeA);
        System.out.println();
        postPrintRec(nodeA);
        System.out.println("\n迭代者为人>>>");
        prePrint(nodeA);
        System.out.println();
        midPrint(nodeA);
        System.out.println();
        postPrint(nodeA);
        System.out.println("\n层序遍历>>>");
        levelPrint(nodeA);
        System.out.println();
        levelPrintPro(nodeA);
        System.out.println("树的高度>>>");
        System.out.println(getTreeHeight(nodeA));
        System.out.println("总节点数和叶子节点数>>>");
        System.out.println(getTotalSize(nodeA));
        System.out.println(getLeafSize(nodeA));
        System.out.println("树第k层节点数>>>");
        System.out.println(getKLevelSize(nodeA, 3));
        System.out.println("指定节点是否在树中>>>");
        System.out.println(isExist(nodeA, "F"));
        System.out.println("两颗树是否相等>>>");
        System.out.println(equalsTree(nodeA,nodeB));
        System.out.println("树镜像>>>");
        mirrorTree(nodeA);
        prePrintRec(nodeA);
    }
    //递归前中后序遍历树
    public static void prePrintRec(TreeNode root){
        if(root == null){return;}
        System.out.print(root.value);
        prePrintRec(root.left);
        prePrintRec(root.right);
    }
    public static void midPrintRec(TreeNode root){
        if(root == null){return;}
        midPrintRec(root.left);
        System.out.print(root.value);
        midPrintRec(root.right);
    }
    public static void postPrintRec(TreeNode root){
        if(root == null){return;}
        postPrintRec(root.left);
        postPrintRec(root.right);
        System.out.print(root.value);
    }
    //非递归前中后序遍历二叉树
    public static void prePrint(TreeNode root){
        if(root == null){return;}
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode node = stack.pop();
            System.out.print(node.value);
            if(node.right != null){stack.push(node.right);}
            if(node.left != null){stack.push(node.left);}
        }
    }
    public static void midPrint(TreeNode root){
        if(root == null){return;}
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        while(!stack.isEmpty() || current != null){
            while(current != null){//找当前节点为根节点的最左节点，依次入栈
                stack.push(current);
                current = current.left;
            }
            TreeNode node = stack.pop();
            System.out.print(node.value);
            if(node.right != null){current = node.right;}
        }
    }
    public static void postPrint(TreeNode root){
        if(root == null){return;}
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> newStack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode node = stack.pop();
            newStack.push(node);
            if(node.left != null){stack.push(node.left);}
            if(node.right != null){stack.push(node.right);}
        }
        while(!newStack.isEmpty()){
            System.out.print(newStack.pop().value);
        }
    }
    //层序遍历及打印每一层节点
    public static void levelPrint(TreeNode root){
        if(root == null){return;}
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            System.out.print(node.value);
            if(node.left != null){queue.add(node.left);}
            if(node.right != null){queue.add(node.right);}
        }
    }
    public static List<List<Object>> levelPrintPro(TreeNode root){
        if(root == null){return null;}
        Queue<TreeNode> queue = new ArrayDeque<>();
        List<List<Object>> lst = new ArrayList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            List<Object> levelLst = new ArrayList<>();
            while(size-- > 0){
                TreeNode node = queue.poll();
                levelLst.add(node.value);
                if(node.left != null){queue.add(node.left);}
                if(node.right != null){queue.add(node.right);}
            }
            lst.add(levelLst);
        }
        return lst;
    }
    //返回树的高度
    public static int getTreeHeight(TreeNode root){
        if(root == null){return 0;}
        int left = getTreeHeight(root.left);
        int right = getTreeHeight(root.right);
        return left > right ? ++left : ++right;
    }
    //返回树的总结点数和叶子节点数
    public static int getTotalSize(TreeNode root){
        if(root == null){return 0;}
        return getTotalSize(root.left) + getTotalSize(root.right) + 1;
    }
    public static int getLeafSize(TreeNode root){
        if(root == null){return 0;}
        if(root.left == null && root.right == null){return 1;}
        return getLeafSize(root.left) + getLeafSize(root.right);
    }
    //返回树第k层节点数
    public static int getKLevelSize(TreeNode root, int k){
        if(root == null){return 0;}
        if(k <= 0){throw new RuntimeException();}
        if(k == 1){return 1;}
        return getKLevelSize(root.left,k-1) + getKLevelSize(root.right, k-1);
    }
    //树中是否存在指定节点
    public static boolean isExist(TreeNode root, Object value){
        if(root == null){return false;}
        if(root.value.toString().equals(value.toString())){return true;}
        return isExist(root.left,value) || isExist(root.right, value);
    }
    //两颗树是否相等
    public static boolean equalsTree(TreeNode root1, TreeNode root2){
        if(root1 == null && root2 == null){return true;}
        if(root1 != null && root2 != null && root1.value.toString().equals(root2.value.toString())
                && equalsTree(root1.left,root2.left) && equalsTree(root1.right,root2.right)){return true;}
        return false;
    }
    public static void mirrorTree(TreeNode root){
        if(root == null)return ;
        if(root.left == null && root.right == null){return ;}
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        mirrorTree(root.left);
        mirrorTree(root.right);
    }
}
