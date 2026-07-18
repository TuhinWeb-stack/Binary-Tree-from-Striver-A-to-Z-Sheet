/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return findLCA(root , p , q);
    }
    public static TreeNode findLCA(TreeNode node , TreeNode p , TreeNode q){
        if(node== null){
            return null;
        }
        if(node==p || node==q){
            return node;
        }
        TreeNode leftResult= findLCA(node.left, p , q);
        TreeNode rightResult= findLCA(node.right, p, q);

        if(leftResult!= null && rightResult!=null){
            return node;
        }
        if(leftResult!=null){
            return leftResult;
        }
        return rightResult;
    }
}