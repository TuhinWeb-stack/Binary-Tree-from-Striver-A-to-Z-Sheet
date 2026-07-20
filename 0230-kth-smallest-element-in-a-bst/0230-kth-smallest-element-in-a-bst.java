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
    public int kthSmallest(TreeNode root, int k) {
        int[] count= new int[1];
        int[] result= new int[1];
        inOrder(root, k, count, result);
        return result[0];
    }
    public static void inOrder(TreeNode node, int k, int[] count, int[] result){
        if(node== null){
            return;
        }
        if(count[0]>=k){
            return;
        }
        inOrder(node.left, k, count, result);
        count[0]++;
        
        if(count[0]==k){
            result[0]= node.val;
            return;
        }
        inOrder(node.right, k, count, result);
    }
}