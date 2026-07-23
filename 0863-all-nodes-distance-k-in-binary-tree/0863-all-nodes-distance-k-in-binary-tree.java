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
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<Integer> result= new ArrayList<>();
        if(root==null){
            return result;
        }
        Map<TreeNode, TreeNode> treeMap= new HashMap<>();
        buildTreeMap(root,null, treeMap);
        Queue<TreeNode> q= new LinkedList<>();
        Set<TreeNode> visited= new HashSet<>();

        int completedDistance=0;
        q.offer(target);
        visited.add(target);

        while(!q.isEmpty()){
            int levelSize= q.size();
            if(completedDistance==k){
                for(int i=0; i<levelSize; i++){
                    result.add(q.poll().val);
                }
                return result;
            }
            
            for(int j=0; j<levelSize; j++){
                TreeNode currNode= q.poll();
                if(currNode.left!=null && !visited.contains(currNode.left)){
                    q.offer(currNode.left);
                    visited.add(currNode.left);
                }
                if(currNode.right!=null && !visited.contains(currNode.right)){
                    q.offer(currNode.right);
                    visited.add(currNode.right);
                }
                TreeNode parent= treeMap.get(currNode);
                if(parent!=null && !visited.contains(parent)){
                    q.offer(parent);
                    visited.add(parent);
                }

            }
            completedDistance++;
        }
        return result;
    }
    public static void buildTreeMap(TreeNode child, TreeNode parent, Map<TreeNode, TreeNode> treeMap){
        if(child==null){
            return;
        }
        treeMap.put(child, parent);
        buildTreeMap(child.left, child, treeMap);
        buildTreeMap(child.right, child, treeMap);
    }
}