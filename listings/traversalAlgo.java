void traversalAlgo(){
	seenNodes.add(root);
	//Find shortest path from the root to each node using BFS
	//Sort the nodes based on their distances from the root
	List<State> sorted = sortWithBFS();
	//While there is a node with uncovered outgoing edges
	while(!sorted.isEmpty()){
		State s = sorted.get(0);
		if(hasUncoveredOutgoingEdge(s)){
			//Take the shortest path from the root to it
			List<Transition> prefix = shortestPathFromRoot(s);
			trace.clear();
			for(Transition t : prefix){
				trace.push(t);
				seenEdges.add(t);
			}
			greedyTraversal(s);
		}else sorted.remove(0);
	} }
void greedyTraversal(State s){
	//Get an uncovered outgoing edge
	Set<Transition> outgoingEdges = graph.outgoingEdgesOf(s);
	Transition t = getUncoveredEdge(outgoingEdges);
	trace.push(t);
	State next = t.getSink();
	seenEdges.add(t);
	boolean stop = !seenNodes.add(next) &&
			!hasUncoveredOutgoingEdge(next);
	if(!stop){
		//Keep taking uncovered edges
		greedyTraversal(next);
	}else{
		//Stop if next is seen with no uncovered outgoing edge
		Stack<Transition> newTest = new Stack<Transition>();
		newTest.addAll(trace);
	}
	trace.pop(); }
