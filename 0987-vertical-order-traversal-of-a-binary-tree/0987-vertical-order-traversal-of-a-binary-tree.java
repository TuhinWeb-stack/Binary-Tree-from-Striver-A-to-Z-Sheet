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
    // WHAT: Small helper record to carry (row, val) together into a column's list.
    // WHY: We need BOTH row and val stored together so we can sort by row first,
    // then by val on ties — a plain List<Integer> would lose the row info needed
    // for correct tie-breaking.
    private static class NodeInfo {
        int row;
        int val;

        NodeInfo(int row, int val) {
            this.row = row;
            this.val = val;
        }
    }
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        // 1. EDGE CASE: Empty tree has nothing to traverse.
        if (root == null) {
            return result;
        }

        // 2. COLUMN MAP: TreeMap auto-sorts keys (columns) in ascending order,
        // so column -2, -1, 0, 1, 2... come out already left-to-right —
        // no manual sorting of columns needed later.
        // WHY: This directly matches the required "leftmost to rightmost" output order.
        TreeMap<Integer, List<NodeInfo>> columnMap = new TreeMap<>();

        // 3. BFS SETUP: We track (node, row, col) together since a plain
        // Queue<TreeNode> would lose position info as we move through levels.
        // WHY: BFS naturally lets us compute row/col by simply +1/-1/+1 from parent,
        // without needing extra global counters.
        Queue<Object[]> queue = new LinkedList<>();
        queue.offer(new Object[]{root, 0, 0}); // node, row, col

        // 4. BFS TRAVERSAL: Visit every node, bucket it by its column.
        while (!queue.isEmpty()) {
            Object[] current = queue.poll();
            TreeNode node = (TreeNode) current[0];
            int row = (int) current[1];
            int col = (int) current[2];

            // WHY: computeIfAbsent creates a new list for a column the first time
            // we see it, otherwise reuses the existing list — avoids manual
            // null-checking boilerplate.
            columnMap.computeIfAbsent(col, k -> new ArrayList<>())
                     .add(new NodeInfo(row, node.val));

            // WHY: Push children with updated row/col so their position is
            // correctly computed relative to the parent, per problem definition.
            if (node.left != null) {
                queue.offer(new Object[]{node.left, row + 1, col - 1});
            }
            if (node.right != null) {
                queue.offer(new Object[]{node.right, row + 1, col + 1});
            }
        }

        // 5. SORT & FLATTEN: For each column (already in left-to-right order
        // thanks to TreeMap), sort its nodes by row first, then by value on ties.
        for (List<NodeInfo> columnNodes : columnMap.values()) {
            // WHY: Comparator chains row comparison first (primary key), then
            // falls back to val comparison (secondary key / tie-breaker) —
            // this directly implements the problem's ordering rule.
            columnNodes.sort((a, b) -> {
                if (a.row != b.row) {
                    return Integer.compare(a.row, b.row);
                }
                return Integer.compare(a.val, b.val);
            });

            // WHY: Extract just the values (drop row info) since that's all
            // the final output format requires.
            List<Integer> columnValues = new ArrayList<>();
            for (NodeInfo info : columnNodes) {
                columnValues.add(info.val);
            }
            result.add(columnValues);
        }

        return result;
    }
}