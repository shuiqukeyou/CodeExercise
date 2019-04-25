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
type1 = find(y==1); % ����һ�����������е���yֵ����1��Ԫ�ص����
type2 = find(y==0); % ����y==0���������
% ��X����y=1����Ŀ�ĵ�1�к͵ڶ�����Ϊ��ͼ��x��y����
% ָ�����ͼ��Ϊx�ͣ��������ͼ����߿�Ϊ2����ָ��ͼ��Ĵ�СΪ7
plot(X(type1, 1), X(type1, 2), 'k+', 'LineWidth', 2, 'MarkerSize', 7)
plot(X(type2, 1), X(type2, 2), 'ko', 'MarkerFaceColor', 'y', 'MarkerSize', 7)







% =========================================================================



hold off;

end
