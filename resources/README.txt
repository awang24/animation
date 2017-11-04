For the design of the EasyAnimator, very minimal changes were made from the previous assignment.  
To facilitate accessing information of the model (by the controller), we added getter methods to
access the fields of the model. Also, in our IShapes interface, we added methods to return the shape
representation in SVGtags and methods to return the start and end SVGtags to help with the
SVG view via double dispatch.  

For the views in this assignment, we created an overall IView interface to hold views,
and had multiple interfaces, ITextView and IVisualView, that extended this interface.  These
interfaces represented the Text View and SVG View in one interface and Visual View in another
interface. Each interface contains methods specific to these types of views. From these interfaces,
we created classes to instantiate the views. The TextualView implemented the ITextView with the
SVGView extending the TextualView because all the code was the same between them except for the
string description method. Lastly, the VisualAnimationView implemented the IVisualView.

Rather than having the model and view access each other, we decided to create controllers that
designate tasks between the model and view.  There is an overall controller interface from which
each controller extends from.  Each controller handles a different view.