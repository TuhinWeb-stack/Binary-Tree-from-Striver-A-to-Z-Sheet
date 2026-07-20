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
    private static int[] index;

    public TreeNode bstFromPreorder(int[] preorder) {
        index= new int[1];
        return build(preorder, Long.MIN_VALUE, Long.MAX_VALUE );
    }
    public static TreeNode build(int[] preorder, long min, long max){
        if(index[0]== preorder.length){
            return null;
        }
        int currentVal= preorder[index[0]];
        if(currentVal<min || currentVal>max){
            return null;
        }
        index[0]++;
        TreeNode node= new TreeNode(currentVal);

        node.left= build(preorder,min, currentVal);
        node.right= build(preorder, currentVal, max);

        return node;
    }
}