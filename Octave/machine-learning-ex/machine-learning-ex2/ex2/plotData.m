function plotData(X, y)
%PLOTDATA Plots the data points X and y into a new figure 
%   PLOTDATA(x,y) plots the data points with + for the positive examples
%   and o for the negative examples. X is assumed to be a Mx2 matrix.

% Create New Figure
figure; hold on;

% ====================== YOUR CODE HERE ======================
% Instructions: Plot the positive and negative examples on a
%               2D plot, using the option 'k+' for the positive
%               examples and 'ko' for the negative examples.
%
type1 = find(y==1); % 返回一个向量，含有的是y值等于1的元素的序号
type2 = find(y==0); % 返回y==0的序号向量
% 将X的中y=1的项目的第1列和第二列作为绘图的x，y坐标
% 指定点的图像为x型，调整点的图像的线宽为2磅，指定图像的大小为7
plot(X(type1, 1), X(type1, 2), 'k+', 'LineWidth', 2, 'MarkerSize', 7)
plot(X(type2, 1), X(type2, 2), 'ko', 'MarkerFaceColor', 'y', 'MarkerSize', 7)







% =========================================================================



hold off;

end
