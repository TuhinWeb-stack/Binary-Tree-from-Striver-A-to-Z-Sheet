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
        return findLCA(root, p , q);
    }
    public static TreeNode findLCA(TreeNode node, TreeNode p , TreeNode q){
        
        if(p.val<node.val && q.val<node.val){
            return findLCA(node.left,p,q);
        }
        if(p.val>node.val && q.val>node.val){
            return findLCA(node.right,p,q);
        }
        return node;

    }
}