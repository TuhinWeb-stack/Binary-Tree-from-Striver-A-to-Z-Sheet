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
    public List<List<Integer>> levelOrder(TreeNode root) {
      List<List<Integer>> result = new ArrayList<>();
        
        // Base Case: If the tree is empty, return the empty result list immediately.
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        // WHAT: Core horizontal search engine loop.
        // WHY: Continues running as long as there is another layer of child nodes beneath us.
        while (!queue.isEmpty()) {
            // CRITICAL STEP: Capture the exact number of nodes belonging to the current level.
            // WHY: The queue size grows dynamically when we add child elements. Locking down 
            // the size now ensures we only pull out nodes belonging to this specific level.
            int levelSize = queue.size();
            List<Integer> currentLevelList = new ArrayList<>();

            // WHAT: Process all nodes on the current horizontal row.
            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                currentLevelList.add(currentNode.val);

                // If a left child exists, stage it in the queue for the next level sweep.
                if (currentNode.left != null) {
                    queue.add(currentNode.left);
                }

                // If a right child exists, stage it in the queue for the next level sweep.
                if (currentNode.right != null) {
                    queue.add(currentNode.right);
                }
            }

            // Save the finished horizontal row layer to our overarching master list.
            result.add(currentLevelList);
        }

        return result;  
    }
}