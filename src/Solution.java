import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */


public class Solution {

    public static void main(String [] args){

        int [] tree = {2147483647};
        Queue<Integer> treeQ = new LinkedList<>();

        for(int i = 0 ; i < tree.length ; i++){
            treeQ.add(tree[i]);
        }

        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(treeQ.poll());
        queue.add(root);

        while(!treeQ.isEmpty()){
            TreeNode node = queue.poll();

            TreeNode left = new TreeNode(treeQ.poll());
            queue.add(left);
            node.left = left;

            if(treeQ.isEmpty()){
                break;
            }
            TreeNode right = new TreeNode(treeQ.poll());
            queue.add(right);
            node.right = right;

        }

        Solution a = new Solution();
        System.out.println(a.isValidBST(root));

    }


    class ResultType{
        boolean isBST;
        int maxValue;
        int minValue;
        public ResultType(boolean isBST, int maxValue, int minValue){
            this.isBST = isBST;
            this.maxValue = maxValue;
            this.minValue = minValue;
        }

    }

    /*
     * @param root: The root of binary tree.
     * @return: True if the binary tree is BST, or false
     */
    public boolean isValidBST(TreeNode root) {

        return helper(root).isBST;

    }

    public ResultType helper(TreeNode root){

        if(root == null){
            return new ResultType(true, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }

        ResultType leftTree = helper(root.left);
        ResultType rightTree = helper(root.right);

        if(!leftTree.isBST || !rightTree.isBST){
            return new ResultType(false, 0, 0);
        }
        if(root.left != null && leftTree.maxValue >= root.val || root.right != null && rightTree.minValue <= root.val){
            return new ResultType(false, 0, 0);
        }
        return new ResultType(true, Math.max(root.val, rightTree.maxValue), Math.min(root.val, leftTree.minValue));
    }
}