function [J, grad] = lrCostFunction(theta, X, y, lambda)
%LRCOSTFUNCTION Compute cost and gradient for logistic regression with 
%regularization
%   J = LRCOSTFUNCTION(theta, X, y, lambda) computes the cost of using
%   theta as the parameter for regularized logistic regression and the
%   gradient of the cost w.r.t. to the parameters. 

% Initialize some useful values
m = length(y); % number of training examples

% You need to return the following variables correctly 
J = 0;
grad = zeros(size(theta));

% ====================== YOUR CODE HERE ======================
% Instructions: Compute the cost of a particular choice of theta.
%               You should set J to the cost.
%               Compute the partial derivatives and set grad to the partial
%               derivatives of the cost w.r.t. each parameter in theta
%
% Hint: The computation of the cost function and gradients can be
%       efficiently vectorized. For example, consider the computation
%
%           sigmoid(X * theta)
%
%       Each row of the resulting matrix will contain the value of the
%       prediction for that example. You can make use of this to vectorize
%       the cost function and gradient computations. 
%
% Hint: When computing the gradient of the regularized cost function, 
%       there're many possible vectorized solutions, but one solution
%       looks like:
%           grad = (unregularized gradient for logistic regression)
%           temp = theta; 
%           temp(1) = 0;   % because we don't add anything for j = 0  
%           grad = grad + YOUR_CODE_HERE (using the temp variable)
%
% 这个函数实质上是执行了若干组的成本和梯度的计算，单级神经网络实际上就是n个逻辑回归
h = sigmoid(X * theta); % sigmoid向量
temp = theta.^2; % 成本正规项
temp(1,1) = 0; % 第一项置0
temp = lambda /2 * sum(temp);% 正规项求和 
J = ((-y' * log(h) - (1-y)' * log(1 - h)) + temp) / m ; # 成本计算（含正规）

temp = theta;
temp(1,1) = 0;% 将lambda_1置0
grad = (X' * (h-y) + lambda * temp)/m;









% =============================================================

grad = grad(:);

end
