#include<iostream>
int c;
int d;
int e;
int main(){
	c = true;
	d = false;
	e = (c && d) || (c && (! c));
	printf("%d", e);
}