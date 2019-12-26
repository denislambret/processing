package growingLine;

import processing.core.PApplet;

public class GrowingLine {

	PApplet p;

	// Constructor
	GrowingLine(PApplet p_) {
		// Retrieve parent reference for processing function calls
		p = p_;

	}
	

var GrowingLine = function(closed=false){
  this.springs = [];
  this.points = [];
  this.closed = closed;
}

GrowingLine.prototype = {
  addPoint: function(x, y, fixed=false, len){
    this.points.push(new Point(x, y, fixed));
    if(this.points.length > 1){
      if(this.closed){
        this.springs.pop();      
      }
      this.springs.push(new Spring(this.points[this.points.length-2],this.points[this.points.length-1],len));
      if(this.closed){
        this.springs.push(new Spring(this.points[this.points.length-1],this.points[0],len));
      }
    }
  },
  insertPoint: function(fixed=false, len0, len1, firstIndex){
    if(this.points.length > 1){
      let midPos = p5.Vector.add(this.points[firstIndex].pos,this.points[firstIndex+1].pos).mult(0.5);
      this.points.splice(firstIndex+1,0,new Point(midPos.x, midPos.y, fixed));
      this.springs.splice(firstIndex, 1, new Spring(this.points[firstIndex],this.points[firstIndex+1],len0));
      this.springs.splice(firstIndex+1, 0, new Spring(this.points[firstIndex+1],this.points[firstIndex+2],len1));
    }
  },
  update: function(dt){
    var self = this;
    for(var s=0; s<this.springs.length; s++){
      this.springs[s].applyForces();
    }
    for(var p=0; p<this.points.length; p++){
      // k-d tree ?
      /*if(p<50){
        this.points[p].checkCollisions(p,this.points.filter(function(){
          return ((p < 50) && (p >= 0)) || ((p > self.points.length-50) && (p < self.points.length)); 
        }));
      } else if(p>this.points.length-50){
        this.points[p].checkCollisions(p,this.points.filter(function(){
          return ((p < 50) && (p >= 0)) || ((p > self.points.length-50) && (p < self.points.length)); 
        }));
      } else {
        this.points[p].checkCollisions(p,this.points.slice(p-50,p+50));
      }*/
      this.points[p].checkCollisions(p,this.points); // Slow
      this.points[p].updatePos(dt);
      this.points[p].resetForces();
    }
  },
  display: function(displayPoints = false){
    stroke(255);
    strokeWeight(3);
    for(var p=1; p<this.points.length; p++){
      line(this.points[p-1].pos.x, this.points[p-1].pos.y, this.points[p].pos.x, this.points[p].pos.y);
      if(displayPoints){
        ellipse(this.points[p-1].pos.x, this.points[p-1].pos.y,5,5);
        ellipse(this.points[p].pos.x, this.points[p].pos.y,5,5);
      }
    }
    if(this.closed) {
      line(this.points[0].pos.x, this.points[0].pos.y, this.points[this.points.length-1].pos.x, this.points[this.points.length-1].pos.y);
    }
  }
}
}
