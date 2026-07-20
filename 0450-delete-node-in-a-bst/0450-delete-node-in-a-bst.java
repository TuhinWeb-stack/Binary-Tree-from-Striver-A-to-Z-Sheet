/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        return deleteee(root, key);
    }
    public static int findMin(TreeNode nodee){
        while(nodee.left!= null){
            nodee= nodee.left;
        }
        return nodee.val;
    }
    public static TreeNode deleteee(TreeNode node , int val){
        if(node== null){
            return null;
        }
        if(val<node.val){
            node.left= deleteee(node.left, val);
        }
        else if(val>node.val){
            node.right= deleteee(node.right, val);
        }
        else{
            if(node.left==null){
                return node.right;
            }
            else if(node.right==null){
                return node.left;
            }
            else{
                int succesorval= findMin(node.right);
                node.val=succesorval;

                node.right= deleteee(node.right, succesorval);
            }
        }
        return node;
    }
}