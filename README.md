# kruskal-anim
Java based animated demonstration of Minimum Spanning Tree Kruskal algorithm using HTML5 Canvas, Javascript and AJAX.

I present here a Java based demonstration of the Minimum Spanning Tree using Kruskal algorithm.

The algorithm itself is implemented in Java, Javascript is only used for initiaization and display on Canvas.

First an undirected graph is randomly created and its largest connected component is extracted (Java side).

Then a minimum spanning tree is built (Java side) with all intermediate results saved as a collection.

This collection is then sent to the browser as a JSON object.

The collection is used for an animated display (browser side).

When deployed on Tomcat the context root is minimum-spanning-tree.

For a step-by-step demonstration please visit this repository:

https://github.com/dubersfeld/kruskal-step

Dominique Ubersfeld, Cachan, France
