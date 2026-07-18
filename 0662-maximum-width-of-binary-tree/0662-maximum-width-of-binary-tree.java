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
    public class queueEntry {
        TreeNode node;
        int index;

        queueEntry(TreeNode node, int index){
            this.node= node;
            this.index= index;
        }
    }
    public int widthOfBinaryTree(TreeNode root) {
        if(root== null){
            return 0;
        }
        int maxWidth=0;
        Queue<queueEntry> queue= new LinkedList<>();
        queue.offer(new queueEntry(root, 0));

        while(!queue.isEmpty()){
            int levelSize= queue.size();
            long firstIndexInlevel= queue.peek().index;
            long firstIndex=0;
            long lastIndex=0;

            for(int i=0; i<levelSize; i++){
                queueEntry current= queue.poll();
                TreeNode node= current.node;
                long miniLevelIndex=current.index-firstIndexInlevel;
                
                if(i==0){
                    firstIndex= miniLevelIndex;
                }
                if(i== levelSize-1){
                    lastIndex= miniLevelIndex;
                }
                if(node.left!=null){
                    queue.offer(new queueEntry(node.left,2*current.index));
                }
                if(node.right!=null){
                    queue.offer(new queueEntry(node.right,2*current.index+1));
                }
            }
            int currentWidth= (int)(lastIndex-firstIndex+1);
            maxWidth= Math.max(maxWidth, currentWidth);
        }
        return maxWidth;
    }
}