package tree;

import link.Node;

public class SearchTreeUtils {
    public static void main(String[] args) {
        TreeNode root = buildSearchTree();
        TreeNode node1 = findTreeNode(root,3);
        prePrint(root);
        System.out.println();
        midPrint(root);
        System.out.println();
        midPrint(node1);
        System.out.println();
        addTreeNode(root,6);
        midPrint(root);
        System.out.println();
        deleteTreeNode(root, 4);
        midPrint(root);

    }
    //构造一颗二叉搜索树
    public static TreeNode buildSearchTree(){
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(8);

        node4.left = node2;
        node4.right = node6;
        node2.left = node1;
        node2.right = node3;
        node6.left = node5;
        node6.right = node7;
        node7.right = node8;
        return node4;
    }
    public static void prePrint(TreeNode root){
        if(root == null){return;}
        System.out.print(root.value+",");
        prePrint(root.left);
        prePrint(root.right);
    }
    //中序遍历打印二叉树
    public static void midPrint(TreeNode root){
        if(root == null){return ;}
        midPrint(root.left);
        System.out.print(root.value+",");
        midPrint(root.right);
    }
    //二叉搜索树的查询
    public static TreeNode findTreeNode(TreeNode root, int val){
        while(root != null){
            if(val < Integer.parseInt(root.value.toString())){
                root = root.left;
            }else if(val > Integer.parseInt(root.value.toString())){
                root = root.right;
            }else{
                return root;
            }
        }
        return null;
    }
    //二叉查找树的插入（最后一定会将新节点查到叶子节点上）
    public static void addTreeNode(TreeNode root, int val){
        if(root == null){
            root = new TreeNode(val);
            return;
        }
        while(root != null){
            if(val < Integer.parseInt(root.value.toString())){
                if(root.left == null){
                    root.left = new TreeNode(val);
                    return;
                }
                root = root.left;
            }else{
                if(root.right == null){
                    root.right = new TreeNode(val);
                    return;
                }
                root = root.right;
            }
        }
    }
    /**
     * 二叉查找树的删除
     * 节点的删除操作分三种情况（假设要删除的节点为p，p的父节点为pp）：
     * 1.p节点左右子节点都不为空，则需要找到右子树的最小节点minR（或者左子树的最大节点），替换p和minR的值，然后删除minR节点
     * 可以利用最小节点一定没有左子树的特点来删除最小节点
     * 2.p节点只有一个子节点，如果pp.left = p,则pp.left = p.child;如果pp.right = p,则pp.right = p.child;
     * 3.p节点是叶子节点，如果pp.left = p,则pp.left = null;如果pp.right = p,则pp.right = null;
     * @param root
     * @param val
     */
    public static void deleteTreeNode(TreeNode root, int val){
        TreeNode p = root;//p指向要删除的节点，刚开始指向根节点
        TreeNode pp = null;//pp是p的parent，刚开始由于p是根，根的父亲为null，因此pp为空
        //寻找要删除的节点p
        while(p != null && Integer.parseInt(p.value.toString()) != val){
            pp = p;
            if(val < Integer.parseInt(p.value.toString())){
                p = p.left;
            }else{
                p = p.right;
            }
        }
        if(p == null){return;}//没找到要删除的节点
        //先判断要删除节点的左右节点都不为空的情况
        if(p.left != null && p.right != null){//找到最小节点，然后将p节点值变为最小节点的值，最后删除最小节点
            TreeNode minP = p.right;//p的右子树最小节点
            TreeNode minPP = p;//p的右子树最小节点的父节点
            while(minP.left != null){//找到最小（左）节点
                minPP = minP;
                minP = minP.left;
            }
            p.value = minP.value;
            p = minP;//这两步并没有删除最小节点，只是为了配合后面逻辑同一处理
            pp = minPP;
        }
        //在判断p只有一个子节点和p为叶子的情况
        TreeNode child;
        if(p.left != null){//根据情况获取p的孩子
            child = p.left;
        }else if(p.right != null){
            child = p.right;
        }else{
            child = null;
        }
        //要删除的根节点，此时pp=null
        if(pp == null){
            root = child;
        }else if(pp.left == p){
            pp.left = child;
        }else{
            pp.right = child;
        }
    }

}
