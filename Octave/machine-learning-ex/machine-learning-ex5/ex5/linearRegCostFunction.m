function [J, grad] = linearRegCostFunction(X, y, theta, lambda)
%LINEARREGCOSTFUNCTION Compute cost and gradient for regularized linear 
%regression with multiple variables
%   [J, grad] = LINEARREGCOSTFUNCTION(X, y, theta, lambda) computes the 
%   cost of using theta as the parameter for linear regression to fit the 
%   data points in X and y. Returns the cost in J and the gradient in grad

% Initialize some useful values
m = length(y); % number of training examples

% You need to return the following variables correctly 
J = 0;
grad = zeros(size(theta));

% ====================== YOUR CODE HERE ======================
% Instructions: Compute the cost and gradient of regularized linear 
%               regression for a particular choice of theta.
%
%               You should set J to the cost and grad to the gradient.
%
h = X * theta; # 计算h
J = sum((h - y) .^ 2)/ (2*m); # 成本
theta_temp = theta;
theta_temp(1,1) = 0; # 去除theat0
J = J + lambda * sum(theta_temp .^ 2)/ (2*m); # 正规项


temp = (h-y)'; # 计算每个theta时都需要使用

grad = (temp * X + lambda * theta_temp')/m;








% =========================================================================

grad = grad(:);

end
